package com.atguigu.scw.manager.service.impl;

import com.atguigu.project.MD5Util;
import com.atguigu.project.MyStringUtils;
import com.atguigu.scw.manager.bean.TUser;
import com.atguigu.scw.manager.bean.TUserExample;
import com.atguigu.scw.manager.dao.TRoleMapper;
import com.atguigu.scw.manager.dao.TUserMapper;
import com.atguigu.scw.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TUserMapper tUserMapper;


    @Override
    public TUser getUserById(Integer id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean register(TUser user) {

        //MD5加密算法,使用后不可逆.
        //使用加密算法
        String digest = MD5Util.digest(user.getUserpswd());
        //将加密后的数据放入数据库
        user.setUserpswd(digest);
         //设置创建数据库的默认的昵称和账号名称一样
        //创建的账号名称不可以修改,但是昵称可以自己修改.
        user.setUsername(user.getLoginacct());
        //设置创建的默认的时间,不可更改.
        //将默认创建的时间放入数据库中,
        user.setCreatetime(MyStringUtils.formatSimpleDate(new Date()));
        int i = 0;
        try{
            i = tUserMapper.insertSelective(user);
        }catch (Exception e){
            /*报错的唯一的可能就是数据库的账号重复
            * 数据库账号是不可以重复的
            * */
            /*如果报错将错误的信息打印到控制台*/
            System.out.println(e);
            return false;
        }
        /*三元运算,若i==1就是truei!=1就是false;*/
        return i==1?true:false;
    }

    /*用户登陆的逻辑处理*/
    @Override
    public TUser login(TUser user) {
        //第一步,先拿到用户名和密码
        /*MD5Util.digest(user.getUserpswd())*/
      /*  user.getLoginacct()*/

        //查询数据库中的用户是否存在
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria criteria = tUserExample.createCriteria();
        //设置查询的条件
        criteria.andLoginacctEqualTo(user.getLoginacct());
        criteria.andUserpswdEqualTo(MD5Util.digest(user.getUserpswd()));

        //可能会查询多用户,数据库中万一有账号密码重复的就会查询多个用户
        //数据库中设置了索引唯一,不可能会出现重复的账号密码
        List<TUser> tUsers = null;
        try {
            /* 可能会出现异常,出现异常,将异常打印在控制台 */
            tUsers = tUserMapper.selectByExample(tUserExample);
        } catch (Exception e) {
            System.out.println(e);
        }
        //如果查询到数据库中的用户的长度为1返回该用户,否则返回用户的值为空
        return tUsers.size()==1?tUsers.get(0):null;
    }

    @Override
    public List<TUser> getAll() {
        //更具条件查询用户,没有条件默认查询所有的用户
        return tUserMapper.selectByExample(null);
    }

    @Override
    public List<TUser> getByCondition(TUserExample example) {
        return tUserMapper.selectByExample(example);
    }

    @Override
    public List<TUser> getAllByCondition(TUserExample example) {
        return tUserMapper.selectByExample(example);
    }

    @Override
    public void delete(String ids) {
        ////批量删除的方法  contains 判断传过来的数据中是否包含逗号
        if (ids.contains(",")){
            //split分割字符串的方法,将字符串以逗号分隔,将分割后的数据的放回的对象为字符串类型
            String[] split = ids.split(",");
            for (String s:split) {
                int i = 0;
                try {
                    i = Integer.parseInt(s);
                } catch (NumberFormatException e) {

                }
                List<Integer> list = new ArrayList<>();
                list.add(i);
                TUserExample example = new TUserExample();
                //创建查询条件
                TUserExample.Criteria criteria = example.createCriteria();
                TUserExample.Criteria criteria1 = criteria.andIdIn(list);
                tUserMapper.deleteByExample(example);

            }
        }else {//单个删除的方法
            tUserMapper.deleteByPrimaryKey(Integer.parseInt(ids));
        }
    }

    @Override
    public void deleteUser(Integer id) {
        tUserMapper.deleteByPrimaryKey(id);

    }

}
