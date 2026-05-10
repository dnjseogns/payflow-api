package com.dhwon.payflow_api.api.dbInfo.service;

import com.dhwon.payflow_api.api.dbInfo.mapper.DbInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbInfoService {

    private final DbInfoMapper dbInfoMapper;

    public String selectSysdate() {
        return dbInfoMapper.selectSysdate();
    }

    public String selectDbVersion() {
        return dbInfoMapper.selectDbVersion();
    }

    public String selectDbUser() {
        return dbInfoMapper.selectDbUser();
    }
}