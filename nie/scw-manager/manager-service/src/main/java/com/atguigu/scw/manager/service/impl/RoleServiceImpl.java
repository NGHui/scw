package com.atguigu.scw.manager.service.impl;

import com.atguigu.scw.manager.bean.TRole;
import com.atguigu.scw.manager.dao.TRoleMapper;
import com.atguigu.scw.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TRoleMapper tRoleMapper;

    @Override
    public List<TRole> selectAllRole() {
        return tRoleMapper.selectByExample(null);
    }

    @Override
    public List<TRole> byUserSelectRole(Integer userId) {
        return tRoleMapper.byUserSelectRole(userId);
    }

    //获取所有角色的权限
    @Override
    public List<TRole> getAllRoleId(Integer id) {
        return tRoleMapper.getAllRoleId(id);
    }


}
