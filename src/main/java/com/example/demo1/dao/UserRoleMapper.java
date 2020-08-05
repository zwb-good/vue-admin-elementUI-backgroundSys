package com.example.demo1.dao;

import com.example.demo1.pojo.Role;
import com.example.demo1.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    int userRoleSelectExist(@Param("roleIds") Integer[] roleIds, @Param("userId") Integer userId);

    List<UserRole> selectUserRole (Integer userId);

    int saveUserRole(@Param("list") List<Role> list, @Param("userId") Integer userId);

    int updateUserRole(@Param("list") List<Role> list, @Param("userId") Integer userId);

    int deleteByRoleIdAndUserId(@Param("roleIds") Integer[] roleIds, @Param("userId") Integer userId);

    int deleteByUserId(@Param("userIds") Integer[] userIds);

}