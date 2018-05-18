package com.example.demo.controller;


import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;

/**
 * @author yangr
 */
@RestController
@RequestMapping("userInfo")
@Api(tags = {"用户操作接口"},description = "userInfoController")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/hello")
    public String hello() {
        return "hello,xxxxxxx";
    }

    @PostMapping("/selectById1/{id}")
    public UserInfo selectById1(@PathVariable Integer id) {

        return userInfoService.selectById(id);
    }

    @ApiOperation(value = "查询用户",notes = "根据用户ID查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true,
            dataType = "Integer", paramType = "query")
    })
    @PostMapping("/selectById")
    public RetResult<UserInfo> selectById(@RequestParam Integer id){
        UserInfo userInfo = userInfoService.selectById(id);
        return RetResponse.makeOKRsp(userInfo);
    }

    @PostMapping("/testException")
    public RetResult<UserInfo> testException(Integer id){
        List a = null;
        a.getSize();
        UserInfo userInfo = userInfoService.selectById(id);
        return RetResponse.makeOKRsp(userInfo);
    }
}
