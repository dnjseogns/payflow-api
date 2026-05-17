package com.dhwon.payflow_api.api.merchant.service;

import com.dhwon.payflow_api.api.merchant.dto.MerchantSelectResponseDto;
import com.dhwon.payflow_api.api.merchant.mapper.MerchantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantMapper merchantMapper;

    public List<MerchantSelectResponseDto> selectMerchantList() {

        return merchantMapper.selectMerchantList();
    }
}