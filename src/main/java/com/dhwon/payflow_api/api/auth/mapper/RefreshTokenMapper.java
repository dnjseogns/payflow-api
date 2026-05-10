package com.dhwon.payflow_api.api.auth.mapper;

import com.dhwon.payflow_api.api.auth.dto.RefreshTokenDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenMapper {
    int mergeRefreshToken(@Param("dto") RefreshTokenDto dto);

    RefreshTokenDto selectRefreshToken(@Param("loginUserId") String loginUserId);

    int deleteRefreshToken(@Param("loginUserId") String loginUserId);
}
