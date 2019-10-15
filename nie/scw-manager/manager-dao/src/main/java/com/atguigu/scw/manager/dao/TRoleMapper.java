package com.atguigu.scw.manager.dao;

import com.atguigu.scw.manager.bean.TRole;
import com.atguigu.scw.manager.bean.TRoleExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TRoleMapper {

    //自定义的方法
    List<TRole> byUserSelectRole(@Param("uid") Integer userId);
    //++++++++++++++++++++++++++++++++++
    int countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    TRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> getAllRoleId(Integer id);
}