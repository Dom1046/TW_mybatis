package com.mybatis.mapper;

import com.mybatis.domain.User_Dto;

import java.util.List;

public interface UserMapper {
    List<User_Dto> selectAll();
}
