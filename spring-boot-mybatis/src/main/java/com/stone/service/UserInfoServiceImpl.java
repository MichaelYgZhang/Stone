package com.stone.service;

import com.stone.bean.UserInfo;
import com.stone.dao.UserInfoDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoDaoMapper userInfoDao;

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        return userInfoDao.deleteById(id) == 1;
    }
}
