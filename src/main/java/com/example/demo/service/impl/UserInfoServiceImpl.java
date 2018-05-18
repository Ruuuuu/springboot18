package com.example.demo.service.impl;

import com.example.demo.core.ret.ServiceException;
import com.example.demo.dao.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangr
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectById(Integer id) {
        UserInfo user = userInfoMapper.selectById(id);
        if(user == null){
            throw new ServiceException("暂无该用户");
        }
        return user;
    }
}
