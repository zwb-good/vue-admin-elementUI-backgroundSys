package com.example.demo1.service;

import com.example.demo1.dao.MenuMapper;
import com.example.demo1.pojo.Menu;
import com.example.demo1.vo.MenuVO;
import com.example.demo1.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
		@Resource
		MenuMapper menuMapper;

		/**
		 * 统计菜单数据
		 * @param menuVO 查询条件
		 * @return
		 */
		public int countMenu(MenuVO menuVO) {
				return menuMapper.countMenu(menuVO);
		}

		/**
		 * 根据角色id字符串查找菜单
		 * @param roleIds 角色id字符串
		 * @return
		 * @throws Exception
		 */
		public List<Menu> selectMenuByRoles(String roleIds) throws  Exception {
				// Utils.buildMenuTree(param1，param2) 把菜单数据转成树形菜单：param1所有菜单数据，param2父级菜单id，最顶级是-1
				return Utils.buildMenuTree(menuMapper.selectMenuByRoles(Utils.toChange(roleIds)),-1);
		}

		/**
		 * 根据角色id字符串和页面path地址，查找该页面角色所拥有的按钮权限
		 * @param roleIds 角色id字符串
		 * @param path 页面浏览器跳转路径
		 * @return
		 */
		public List<Menu> selectButton(String roleIds,String path) {
				return menuMapper.selectButton(Utils.toChange(roleIds),path);
		}

		/**
		 * 查找菜单列表
		 * @param menuVO 查找条件
		 * @return
		 * @throws Exception
		 */
		public List<Menu> selectMenu(MenuVO menuVO) throws  Exception {
				return menuMapper.selectMenu(menuVO);
		}

		/**
		 * 更新惨淡信息
		 * @param menu 更新信息
		 * @return
		 */
		public int updateMenu(Menu menu) {
				return menuMapper.updateMenu(menu);
		}

		/**
		 * 新加菜单信息
		 * @param menu
		 * @return
		 */
		public int saveMenu(Menu menu) {
				return menuMapper.saveMenu(menu);
		}

		/**
		 * 删除菜单
		 * @param ids
		 * @return
		 */
		public int deleteMenu(String ids) {
				return menuMapper.deleteMenu(Utils.toChange(ids));
		}
}
