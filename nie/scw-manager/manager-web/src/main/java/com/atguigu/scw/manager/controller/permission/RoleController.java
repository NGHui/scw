package com.atguigu.scw.manager.controller.permission;

import com.atguigu.scw.manager.bean.TPermission;
import com.atguigu.scw.manager.bean.TRole;
import com.atguigu.scw.manager.service.PermissionService;
import com.atguigu.scw.manager.service.RoleService;
import com.atguigu.scw.manager.service.UserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理角色模块的请求
 */
@Controller
@RequestMapping("/permission/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    PermissionService permissionService;

    /**
     * 使用ajax提交方式,不需要跳转页面
     * @param rids 需要添加权限的id
     * @param uid 用户传过来的id
     */
    @ResponseBody
    @RequestMapping("/assignrole")
    public String userRole(@RequestParam(value = "rids")String rids,@RequestParam(value = "uid")Integer uid,@RequestParam(value = "data") String data){
        //为某个用户添加一些角色
        if ("add".equals(data)){
            userRoleService.addRole(rids, uid);
            System.out.println("添加完成...");
        }else if ("remove".equals(data)){
            userRoleService.removeRole(rids,uid);
            System.out.println("删除成功...");
        }
        return "";
    }

    /*
     *
     * @param uid 用户id
     * @return 分配权限的页面
     */
   @RequestMapping("/toAssignRolePage")
    public String goToAssignRole(@RequestParam(value = "uid") Integer uid, Model model){
        //1.查出所有的权限

        System.out.println("要去权限分配页面，用户id："+uid);

        List<TRole> roles = roleService.selectAllRole();


        //2.查出当前用户拥有权限,将当前的用户放入map集合中
        List<TRole> userRoles = roleService.byUserSelectRole(uid);
        Map<Integer,TRole> map = new HashMap<>();
        for (TRole tRole:userRoles) {
            //将每一个角色的权限放入到map集合中
            map.put(tRole.getId(),tRole);
        }

        //3.查询出用户未拥有的权限,将未拥有的权限放入集合中
        List<TRole> unRoles = new ArrayList<>();
        for (TRole tRole:roles) {
            if (!map.containsKey(tRole.getId())){
                unRoles.add(tRole);
            }
        }

        //将已经拥有的权限和为拥有的权限放在model中,返回给前端的页面
        model.addAttribute("unroles",unRoles);
        model.addAttribute("roles",userRoles);

        return "manager/permission/assignRole";
    }

    /**
     * 来到角色列表页面
     *
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @return
     */
    @RequestMapping("/list")
    public String toRolePage(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                             @RequestParam(value = "ps", defaultValue = "10") Integer ps, Model model,
                             @RequestParam(value = "sp", defaultValue = "") String search) {

        PageHelper.startPage(pn, ps);
        List<TRole> role = roleService.selectAllRole();
        PageInfo<TRole> info = new PageInfo<>(role, 5);
        model.addAttribute("role_info", info);

        return "manager/permission/role";
    }

    /**
     * 返回所有权限的json数据
     *
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @return
     */
    @ResponseBody
    @RequestMapping("/json")
    public List<TPermission> getAllPermission() {
        // 返回所有的权限
        return permissionService.getPermissions();
    }
    // /permission/role/perm/4
    @ResponseBody
    @RequestMapping("/perm/{id}")
    public List<TPermission> getRolePermisson(@PathVariable("id") Integer rid) {
        List<TPermission> permissions = permissionService.getRolePermission(rid);
        return permissions;
    }


}
