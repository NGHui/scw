package com.atguigu.scw.manager.service;

import com.atguigu.scw.manager.bean.TPermission;

import java.util.List;

public interface PermissionService {

    /**
     * 获取到所有的菜单
     * @return
     */
    public List<TPermission> getAllMenus();

    List<TPermission> getPermissions();

    List<TPermission> getRolePermission(Integer rid);
}
