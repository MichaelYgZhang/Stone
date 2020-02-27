package com.stone.service;

import com.stone.bean.UserInfo;

import java.util.List;

public interface UserInfoService {

    /**
     * 查询所有用户信息
     * @return 所有用户信息列表
     */
    List<UserInfo> findAll();

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    Boolean deleteById(Long id);
}
