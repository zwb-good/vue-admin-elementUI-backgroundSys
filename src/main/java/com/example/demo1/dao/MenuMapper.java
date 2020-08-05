package com.example.demo1.dao;

import com.example.demo1.pojo.Menu;

import java.util.List;

import com.example.demo1.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectMenu(MenuVO menuVO);

    int countMenu(MenuVO menuVO);

    List<Menu> selectMenuByRoles(@Param("roleIds") Integer[] roleIds);

    List<Menu> selectButton(@Param("roleIds") Integer[] roleIds,@Param("path") String path);

    int menuSelectExist(Menu record);

    int saveMenu(Menu record);

    int updateMenu(Menu record);

    int deleteMenu(@Param("ids") Integer[] ids);
}