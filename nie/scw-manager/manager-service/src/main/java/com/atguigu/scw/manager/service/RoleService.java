package com.atguigu.scw.manager.service;

import com.atguigu.scw.manager.bean.TRole;

import java.util.List;

public interface RoleService {

    //查询所有的角色
    List<TRole> selectAllRole();

    //通过用户查询角色
    List<TRole> byUserSelectRole(Integer userId);


    //获取所有角色的权限的id
    List<TRole> getAllRoleId(Integer id);


}
