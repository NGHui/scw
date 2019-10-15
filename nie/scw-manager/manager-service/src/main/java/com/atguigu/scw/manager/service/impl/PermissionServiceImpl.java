package com.atguigu.scw.manager.service.impl;

import com.atguigu.scw.manager.bean.TPermission;
import com.atguigu.scw.manager.bean.TPermissionExample;
import com.atguigu.scw.manager.bean.TRolePermission;
import com.atguigu.scw.manager.dao.TPermissionMapper;
import com.atguigu.scw.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private TPermissionMapper tPermissionMapper;


    @Override
    public List<TPermission> getAllMenus() {
        //保存父级菜单
        List<TPermission> menus = new ArrayList<>();
        //
        Map<Integer,TPermission> map = new HashMap<>();

        //selectByExample()如果不传任何条件就是查询所有
        //获取所有的菜单:数据库中18个的菜单
        List<TPermission> list = tPermissionMapper.selectByExample(null);

        for (TPermission tPermission : list ) {
            //将获取的每一个菜单的元素保存到map中
            map.put(tPermission.getId(),tPermission);
        }

        for (TPermission tPermission: list) {
            if (tPermission.getPid()==0){
                //父级菜单,将父级菜单添加到menus集合中
                menus.add(tPermission);
            }else {//拿到子菜单,添加到父菜单中
                //拿到父菜单,以pid的值作为map中的菜单id，就是父菜单
               Integer pid = tPermission.getPid();
                TPermission p_menu = map.get(pid);
                // 拿到当前父菜单的子菜单；子菜单会有一些额外的问题
                // 这个list第一次获取是没有的，如果添加上一次以后。这个list是有的
                List<TPermission> childs = p_menu.getChilds();
                if (childs!=null){
                    //当前有子菜单
                    childs.add(tPermission);
                }else {
                    //当前没有子菜单
                    childs = new ArrayList<>();
                    //添加当前子菜单
                    childs.add(tPermission);
                    //将当前整理好的childs设置进去
                    p_menu.setChilds(childs);
                }
            }
        }
        return menus;
    }

    @Override
    public List<TPermission> getPermissions() {
        return tPermissionMapper.selectByExample(null);
    }

    /**
     * 查询角色对应的权限
     */
    @Override
    public List<TPermission> getRolePermission(Integer rid) {

        return tPermissionMapper.getRolePermission(rid);
    }


}
