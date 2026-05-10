package com.dhwon.payflow_api.api.dbInfo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbInfoMapper {

    String selectSysdate();

    String selectDbVersion();

    String selectDbUser();
}
