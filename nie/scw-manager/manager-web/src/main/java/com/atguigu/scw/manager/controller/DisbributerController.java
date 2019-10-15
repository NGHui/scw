package com.atguigu.scw.manager.controller;

import com.atguigu.scw.manager.bean.TPermission;
import com.atguigu.scw.manager.constant.Constants;
import com.atguigu.scw.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 为静态化页面
 * 调度中心
 */
@Controller
public class DisbributerController {

    @Autowired
    private PermissionService permissionService;

    /*成功主页面的常量,主页面*/
    private final String MANAGER_MAIN = "manager/main";

    /**
     *
     * @param session 用于判断用户是否登陆,如果用户登陆session狱中会有user对象,没有登陆直接访问session狱中是null
     * @return main.jsp页面
     */
    @RequestMapping(value = "/main.html")
    public String toMian(HttpSession session){
        //获取session狱中的数据
        Object obj = session.getAttribute(Constants.LOGIN_USER);
        //校验用户是否登陆
        if (obj==null){
            //用户没有登陆
            return "redirect:login.jsp";
        }else {//用户登陆了,来到菜单的主页面
            //用户登陆后来到主页面,先判断session狱中有没有菜单的数据
            // session狱中有菜单的数据就不用去数据库中查早数据了
            if (session.getAttribute(Constants.USER_MENUS)==null){
                //1.先查出所有的菜单,在页面进行显示
                List<TPermission> allMenus = permissionService.getAllMenus();
                //将查到的menus放在session狱中,放在session狱中,只需要去数据库查询一次
                //如果放在request狱中每次来到这个页面将去数据库查询一次,降低了效率
                session.setAttribute(Constants.USER_MENUS,allMenus);
            }
            return MANAGER_MAIN;
        }

    }

}
