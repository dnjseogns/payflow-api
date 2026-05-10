package com.dhwon.payflow_api.api.dbInfo.controller;

import com.dhwon.payflow_api.api.dbInfo.service.DbInfoService;
import com.dhwon.payflow_api.response.annotation.UseCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dbInfo")
public class DbInfoController {

    private final DbInfoService dbInfoService;

    @GetMapping("/sysdate")
    @UseCommonResponse
    public String selectSysdate() {
        return dbInfoService.selectSysdate();
    }

    @GetMapping("/dbVersion")
    @UseCommonResponse
    public String selectDbVersion() {
        return dbInfoService.selectDbVersion();
    }
    @GetMapping("/dbUser")
    @UseCommonResponse
    public String selectCurrentUser() {
        return dbInfoService.selectDbUser();
    }

}