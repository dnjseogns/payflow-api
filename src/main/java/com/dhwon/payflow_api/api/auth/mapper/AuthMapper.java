package com.dhwon.payflow_api.api.auth.mapper;

import com.dhwon.payflow_api.api.auth.dto.AuthRequestDto;
import com.dhwon.payflow_api.api.auth.dto.AuthResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {
    AuthResponseDto login(@Param("dto") AuthRequestDto authRequestDto);
}

