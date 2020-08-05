package com.example.demo1.controller;

import com.example.demo1.pojo.Menu;
import com.example.demo1.vo.MenuVO;
import com.example.demo1.service.MenuService;
import com.example.demo1.utils.JsonRs;
import com.example.demo1.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
		@Resource
		MenuService menuService;

		/**
		 * 获取菜单列表
		 * @param menuVO 查询条件
		 * @param isTree 是否生成菜单树
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/selectMenu")
		@ResponseBody
		public JsonRs selectMenu(MenuVO menuVO, @RequestParam(value = "isTree",defaultValue = "false") Boolean isTree) throws  Exception {
				List<Menu> list = menuService.selectMenu(menuVO);
				int total = menuService.countMenu(menuVO);
				if(isTree){
						// 生成菜单树，list：菜单列表，-1：顶级菜单parentId值
						list = Utils.buildMenuTree(list,-1);
				}
				return new JsonRs(2000,true,"查询成功",total,list);
		}

		/**
		 * 根据roleIds获取菜单数据
		 * @param roleIds
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/selectMenuByRoles")
		@ResponseBody
		public JsonRs selectMenuByRoles(String roleIds) throws  Exception {
				List<Menu> list = null;
				try {
						list = menuService.selectMenuByRoles(roleIds);
						return new JsonRs(2000,true,"查询成功",list.size(),list);
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"查询失败");
				}
		}

		/**
		 * 根据roleIds和path跳转路径获取页面按钮权限
		 * @param roleIds
		 * @param path
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/selectButton")
		@ResponseBody
		public JsonRs selectButton(String roleIds,String path) throws  Exception {
				List<Menu> list = null;
				try {
						list = menuService.selectButton(roleIds,path);
						return new JsonRs(2000,true,"查询成功",list.size(),list);
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"查询失败");
				}
		}

		/**
		 * 更新菜单信息
		 * @param menu
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/updateMenu")
		@ResponseBody
		public JsonRs updateMenu(Menu menu) throws  Exception {
				try {
						menuService.updateMenu(menu);
						return new JsonRs(2000,true,"更新成功");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"更新失败");
				}
		}

		/**
		 * 添加菜单信息
		 * @param menu
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/saveMenu")
		@ResponseBody
		public JsonRs saveMenu(Menu menu) throws  Exception {
				try {
						menuService.saveMenu(menu);
						return new JsonRs(2000,true,"添加成功");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"添加失败");
				}
		}

		/**
		 * 根据ids删除菜单，可以批量删除
		 * @param ids
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/deleteMenu")
		@ResponseBody
		public JsonRs deleteMenu(String ids) throws  Exception {
				try {
						menuService.deleteMenu(ids);
						return new JsonRs(2000,true,"删除成功");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"删除失败");
				}
		}
}
