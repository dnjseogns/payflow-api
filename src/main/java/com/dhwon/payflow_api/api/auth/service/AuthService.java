package com.dhwon.payflow_api.api.auth.service;

import com.dhwon.payflow_api.api.auth.dto.*;
import com.dhwon.payflow_api.api.auth.mapper.AuthMapper;
import com.dhwon.payflow_api.api.auth.mapper.RefreshTokenMapper;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.mapper.UserMapper;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.exception.CustomException;
import com.dhwon.payflow_api.jwt.JwtProvider;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;

    public AuthResponseDto login(AuthRequestDto authRequestDto){
        AuthResponseDto userInfo = authMapper.login(authRequestDto);

        if(userInfo == null){
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        String accessToken = jwtProvider.createToken(userInfo.getUserId(), userInfo.getRoleCode());
        String refreshToken = jwtProvider.createRefreshToken(userInfo.getUserId());

        //refresh token db저장
        refreshTokenMapper.mergeRefreshToken(
                RefreshTokenDto.builder()
                        .userId(userInfo.getUserId())
                        .refreshToken(refreshToken)
                        .expireDate(jwtProvider.getExpireDate(refreshToken))
                        .build()
        );

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .roleCode(userInfo.getRoleCode())
                .build();
    }




    public ReissueResponseDto reissue(ReissueRequestDto dto) {

        // 1. DB refresh token 조회
        RefreshTokenDto saved =
                refreshTokenMapper.selectRefreshToken(dto.getUserId());

        if (saved == null) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        // 2. refresh token 일치 검증
        if (!saved.getRefreshToken().equals(dto.getRefreshToken())) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        // 3. JWT 검증 (만료 체크)
        jwtProvider.validateToken(dto.getRefreshToken());

        // 4. userId 추출
        String userId = jwtProvider.getUserId(dto.getRefreshToken());
        
        // 5. access token 재발급을 위한 roleCode 조회
        UserResponseDto authRequestDto = userMapper.selectUserDetail(userId);
        if(authRequestDto == null){
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }
        String roleCode = authRequestDto.getRoleCode();

        // 6. access token 재발급
        String newAccessToken = jwtProvider.createToken(userId, roleCode);

        // 7. refresh token 재발급
        String newRefreshToken = jwtProvider.createRefreshToken(userId);

        // 8. refresh token DB 업데이트
        refreshTokenMapper.mergeRefreshToken(
                RefreshTokenDto.builder()
                        .userId(userId)
                        .refreshToken(newRefreshToken)
                        .expireDate(jwtProvider.getExpireDate(newRefreshToken))
                        .build()
        );

        return ReissueResponseDto.builder()
                .userId(userId)
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
