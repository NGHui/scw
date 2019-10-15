package com.atguigu.scw.manager.dao;

import com.atguigu.scw.manager.bean.TReturn;
import com.atguigu.scw.manager.bean.TReturnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TReturnMapper {
    int countByExample(TReturnExample example);

    int deleteByExample(TReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    List<TReturn> selectByExample(TReturnExample example);

    TReturn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByExample(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);
}