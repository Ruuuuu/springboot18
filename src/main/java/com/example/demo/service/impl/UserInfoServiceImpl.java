package com.example.demo.service.impl;

import com.example.demo.core.ret.ServiceException;
import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.UserInfoMapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangr
 */
@Service
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo selectById(String id) {
        UserInfo user = userInfoMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new ServiceException("暂无该用户");
        }
        return user;
    }


}
