package com.example.demo1.dao;

import com.example.demo1.pojo.User;

import java.util.List;

import com.example.demo1.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    List<User> selectUser(UserVO userVO);

    User login(@Param("username") String username, @Param("password") String password);

    User findByUsername(@Param("username") String username);

    int count(UserVO userVO);

    Integer userSelectExist(@Param("username") String username);

    int saveUser(User user);

    int updateUser(UserVO userVO);

    int softDeleteMore(@Param("ids") Integer[] ids);

    int deleteUser(@Param("ids") Integer[] ids);
}