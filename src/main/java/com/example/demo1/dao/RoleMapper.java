package com.example.demo1.dao;

import com.example.demo1.pojo.Role;

import java.util.List;

import com.example.demo1.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

		List<Role> selectRole(RoleVO roleVO);

		int countRole(RoleVO roleVO);

		int RoleSelectExist(Role role);

		int saveRole(Role role);

		int deleteRole(@Param("ids") Integer[] ids);

		int updateRole(Role role);
}