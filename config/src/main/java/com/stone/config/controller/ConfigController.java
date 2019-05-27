package com.stone.config.controller;

import com.stone.config.config.Config;
import com.stone.config.beans.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private MyBean myBean;

    @Autowired
    private Config config;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        System.out.println("using environment: " + config.getEnvironment());
        System.out.println("name: " + config.getName());
        System.out.println("servers: " + config.getServers());

        return "hello, config." + myBean.getName();
    }
}
