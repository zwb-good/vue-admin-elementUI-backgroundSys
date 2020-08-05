package com.example.demo1.dao;

import com.example.demo1.pojo.RoleMenu;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {

    List<RoleMenu> selectRoleMenu(RoleMenu roleMenu) throws Exception;

    int roleMenuSelectExist(RoleMenu roleMenu);

    int deleteByRoleIdAndMenuIds(@Param("roleId") Integer roleId,@Param("menuIds") Integer[] menuIds);

    int deleteByRoleId (@Param("ids") Integer[] ids);

    int deleteRoleMenu (@Param("id") Integer id);

    int saveRoleMenu(@Param("roleId") Integer roleId,@Param("menuIds") Integer[] menuIds);

}