package com.stone.controller;

import com.stone.bean.UserInfo;
import com.stone.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/userInfoList")
    public List<UserInfo> userInfoList() {
        return userInfoService.findAll();
    }
}
