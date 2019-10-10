package com.demons.shiro.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.demons.common.PageTool.PageBean;
import com.demons.shiro.model.User;

public interface UserMapper {
    int insert(User record);

	int insertSelective(User record);

	int deleteByPrimaryKey(String id);

    User selectByPrimaryKey(String id);
    
    public List<User> selectList(@Param("map")Map<String, Object> map,@Param("page")PageBean<User> page);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int selectListNum();
    
    List<User> selectAll();
    
    User selectOne(String id);
}