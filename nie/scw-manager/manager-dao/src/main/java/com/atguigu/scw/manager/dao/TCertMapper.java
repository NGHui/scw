package com.atguigu.scw.manager.dao;

import com.atguigu.scw.manager.bean.TCert;
import com.atguigu.scw.manager.bean.TCertExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCertMapper {
    int countByExample(TCertExample example);

    int deleteByExample(TCertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCert record);

    int insertSelective(TCert record);

    List<TCert> selectByExample(TCertExample example);

    TCert selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCert record, @Param("example") TCertExample example);

    int updateByExample(@Param("record") TCert record, @Param("example") TCertExample example);

    int updateByPrimaryKeySelective(TCert record);

    int updateByPrimaryKey(TCert record);
}