package com.dhwon.payflow_api.api.merchant.mapper;

import com.dhwon.payflow_api.api.merchant.dto.MerchantSelectResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {

    List<MerchantSelectResponseDto> selectMerchantList();
}