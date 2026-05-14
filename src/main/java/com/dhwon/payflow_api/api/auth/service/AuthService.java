package com.dhwon.payflow_api.api.auth.service;

import com.dhwon.payflow_api.api.auth.dto.*;
import com.dhwon.payflow_api.api.auth.mapper.AuthMapper;
import com.dhwon.payflow_api.api.auth.mapper.RefreshTokenMapper;
import com.dhwon.payflow_api.api.user.dto.UserResponseDto;
import com.dhwon.payflow_api.api.user.mapper.UserMapper;
import com.dhwon.payflow_api.cmm.auth.CookieUtil;
import com.dhwon.payflow_api.cmm.auth.context.UserContext;
import com.dhwon.payflow_api.exception.CustomException;
import com.dhwon.payflow_api.jwt.JwtConst;
import com.dhwon.payflow_api.jwt.JwtProvider;
import com.dhwon.payflow_api.response.enums.ResponseCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;

    public AuthResponseDto login(AuthRequestDto authRequestDto, HttpServletResponse response){
        AuthResultDto dbResult = authMapper.login(authRequestDto);

//        if (dbResult == null ||
//                !passwordEncoder.matches(authRequestDto.getUserPw(), dbResult.getUserPw())) {
//            throw new CustomException(ResponseCode.UNAUTHORIZED);
//        }

        String accessToken = jwtProvider.createToken(dbResult.getUserId(), dbResult.getRoleCode());
        String refreshToken = jwtProvider.createRefreshToken(dbResult.getUserId());

        //refresh token db저장
        refreshTokenMapper.mergeRefreshToken(
                RefreshTokenDto.builder()
                        .userId(dbResult.getUserId())
                        .refreshToken(refreshToken)
                        .expireDate(jwtProvider.getExpireDate(refreshToken))
                        .build()
        );

        //cookie에 refreshToken 넣어줌
        Cookie refreshTokenCookie = CookieUtil.createCookie(jwtProvider.getRefreshTokenName(), refreshToken, (int)jwtProvider.getExpiration());
        response.addCookie(refreshTokenCookie);


        AuthResultDto authReturn = AuthResultDto
                .builder()
                .userId(dbResult.getUserId())
                .userName(dbResult.getUserName())
                .roleCode(dbResult.getRoleCode())
                .build();

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .authResultDto(authReturn)
                .build();
    }




    public ReissueResponseDto reissue(ReissueRequestDto dto, HttpServletRequest request, HttpServletResponse response) {

        // 1. DB refresh token 조회
        RefreshTokenDto saved =
                refreshTokenMapper.selectRefreshToken(dto.getUserId());

        if (saved == null) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        // 2. refresh token 일치 검증
        String refreshTokenValue = CookieUtil.getCookie(request, jwtProvider.getRefreshTokenName()).getValue();
        if (!saved.getRefreshToken().equals(refreshTokenValue)) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        // 3. JWT 검증 (만료 체크)
        jwtProvider.validateToken(refreshTokenValue);

        // 4. userId 추출
        String userId = jwtProvider.getUserId(refreshTokenValue);
        
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

        // 9. cookie에 refreshToken 넣어줌
        Cookie newRefreshTokenCookie = CookieUtil.createCookie(jwtProvider.getRefreshTokenName(), newRefreshToken, (int)jwtProvider.getExpiration());
        response.addCookie(newRefreshTokenCookie);

        return ReissueResponseDto.builder()
                .userId(userId)
                .accessToken(newAccessToken)
                .build();
    }
}
