package com.dhwon.payflow_api.api.code.mapper;

import com.dhwon.payflow_api.api.code.dto.CodeSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {

    List<CodeSelectResponseDto> selectCodeList();

}