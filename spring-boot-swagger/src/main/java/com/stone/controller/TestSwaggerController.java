package com.stone.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/swagger/test")
@Api("Swagger测试")
public class TestSwaggerController {


    @ApiOperation("post测试")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String")})
    @PostMapping(value = "/doPost")
    public String doPost(@RequestParam String name) {
        return String.format("Do POST. name %s", name);
    }


    @ApiOperation("get测试")
    @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/doGet/{name}")
    public String doGet(@PathVariable(value = "name")String name) {
        return String.format("Do GET. name %s", name);
    }

    @ApiOperation("UserModel测试")
    @ApiImplicitParam(name = "user", value = "User实体", required = true, dataType = "User")
    @PostMapping("/doUser")
    public String doUser(@RequestBody User user) {
        return String.format("name: %s, age: %d", user.getName(), user.getAge());
    }

    @ApiIgnore
    @GetMapping("/hello")
    public String hello() {
        return "Hello Swagger test";
    }
}

@Data
class User {
    private String name;
    private Integer age;
}