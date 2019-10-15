package com.atguigu.scw.manager.service;

import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.bean.TUserExample;

import java.util.List;

public interface UserService {

    public TUser getUserById(Integer id);

    Boolean register(TUser user);

    TUser login(TUser user);

    //查询所有用户的方法
    List<TUser> getAll();

    List<TUser> getByCondition(TUserExample example);

    List<TUser> getAllByCondition(TUserExample example);

    void delete(String ids);


    void deleteUser(Integer id);



}
