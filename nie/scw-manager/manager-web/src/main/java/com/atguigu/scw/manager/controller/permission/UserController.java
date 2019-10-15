package com.atguigu.scw.manager.controller.permission;

import com.atguigu.scw.manager.bean.TRole;
import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.bean.TUserExample;
import com.atguigu.scw.manager.constant.Constants;
import com.atguigu.scw.manager.service.RoleService;
import com.atguigu.scw.manager.service.UserRoleService;
import com.atguigu.scw.manager.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/permission/user")
public class UserController {

    /*成功主页面的常量*/
    private final String MANAGER_MAIN = "manager/main";

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Integer id,Model model){
           //先把你选中的角色所拥有的权限删除
           //List<TRole> roleId = roleService.getAllRoleId(8);

            //System.out.println(roleId);
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
           model.addAttribute("msg","该用户拥有权限,需要删除先将角色的权限删除!");

        }

        return "redirect:/permission/user/list";
    }

    @RequestMapping("/del")
    public String delete(@RequestParam(value = "ids",defaultValue = "") String ids){
        //进行删除的操作,如果传过来的数据不是空字符串,进行删除的操作
        if (!ids.trim().equals("")){
            userService.delete(ids);
        }
        //删除后回到查询所有的页面
        return "redirect:/permission/user/list";
    }


    /**
     *
     * @param pn 开始的页码
     * @param ps 页面显示的大小
     * @param model 将每一页的数据传传入显示的前端的页面
     * @return user.jsp页面
     */
    @RequestMapping("/list")
    public String queryAll(
            /*分页的条件,默认初始页面是第一页,一页显示10个数据*/
            @RequestParam(value = "pn",defaultValue = "1") Integer pn,
            @RequestParam(value = "ps",defaultValue = "10") Integer ps,
            /*前后端的交互*/
            Model model,
             /*查询的参数*/
             @RequestParam(value = "sp",defaultValue = "")String search){
        //使用分页的插件,来实现分页的操作
        PageHelper.startPage(pn,ps);
        //不带条件的查询
        //List<TUser> list =  userService.getAll();
        //带条件的查询
        TUserExample example = new TUserExample();
        //创建查询的条件,按照用户的名字或者用户的账号查询用户的信息
        TUserExample.Criteria criteria1 = example.createCriteria();
        TUserExample.Criteria criteria2 = example.createCriteria();

        if (!search.trim().equals("")){
            //查询的条件一
            criteria1.andLoginacctLike("%"+search+"%");
            //查询的条件二
            criteria2.andUsernameLike("%"+search+"%");
        }
        example.or(criteria2);

        List<TUser> list = userService.getByCondition(example);
        System.out.println(list);
        /*显示页码的数量为5个*/
        PageInfo<TUser> info = new PageInfo<>(list, 5);
        System.out.println("分页逻辑");
        model.addAttribute("user_info",info);
        model.addAttribute("sp",search);
        return "manager/permission/user";
    }

    /**
     *
     * @param user 前端向后端传递的user数据
     * @param session 用于将错误的信息添加到session域中
     * @return 登陆成功或者失败的页面
     */
    @RequestMapping("/login")
    public String login(TUser user,HttpSession session){
        //u表示登陆成功的用户
        //user 前端传过来的用户的数据
        TUser u = userService.login(user);

        //登陆失败
        if (u==null){
            //将数据打回显在前端的页面
            //使用重定向技术,将数据保存在session狱中,防止表单中的数据重复提交
            session.setAttribute("errorUser",user);
            session.setAttribute("loginError","登陆失败");
            return "redirect:/login.jsp";
        }
        //登陆成功
        session.setAttribute(Constants.LOGIN_USER,u);
        return "redirect:/main.html";
    }

    /**
     *
     * @param user 前端像后端传递的数据
     * @param model
     * @param session 发生错误将数据回显到前端的页面
     * @return 成功或者失败的页面
     */
    @RequestMapping("/reg")
    public String reg(TUser user, Model model, HttpSession session){
        //1.注册用户
       Boolean flag =  userService.register(user);

       if (flag){
           /*使用pojo中设置的常量*/
           /*将user放入session中*/
           session.setAttribute(Constants.LOGIN_USER,user);
           return "redirect:/main.html";
       }else {
          /* model.addAttribute("regError","用户名已经被使用");*/
           session.setAttribute("regError","用户名已经被使用");
           return "redirect:/reg.jsp";

       }
    }
}
