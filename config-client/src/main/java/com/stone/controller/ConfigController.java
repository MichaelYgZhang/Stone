package com.stone.controller;

import com.stone.config.config.Config;
import com.stone.config.beans.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
@RefreshScope   // 想要更新，需要先调用 curl -X POST http://localhost:8080/refresh
public class ConfigController {

    @Autowired
    private MyBean myBean;

    @Autowired
    private Config config;

    //从git的config的仓库中获取
    @Value("${info.profile}")
    private String profile;

    @Value("${info.from}")
    private String infoFrom;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("using environment: " + config.getEnvironment());
        System.out.println("name: " + config.getName());
        System.out.println("servers: " + config.getServers());

        return "hello, config." + myBean.getName() +
                ",From github config repo info.profile:" + profile + ",info.from:" + infoFrom;
    }
}
