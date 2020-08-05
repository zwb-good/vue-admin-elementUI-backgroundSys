package com.example.demo1.controller;

import com.example.demo1.pojo.Role;
import com.example.demo1.pojo.RoleMenu;
import com.example.demo1.service.RoleMenuService;
import com.example.demo1.utils.JsonRs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/rolemenu")
public class RoleMenuController {
		@Resource
		RoleMenuService roleMenuService;

		/**
		 * 查找角色和菜单之间的联系
		 * @param roleMenu
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping("/selectRoleMenu")
		public JsonRs selectRoleMenu(RoleMenu roleMenu) throws Exception {
				List<RoleMenu> list = null;
				try {
						list = roleMenuService.selectRoleMenu(roleMenu);
						int total = list.size();
						return new JsonRs(2000,true,"查询成功",total,list);
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"查询错误");
				}
		}

		/**
		 * 修改角色和菜单之间的权限
		 * @param role
		 * @param addArry
		 * @param deletaArry
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping(value = "/roleMenuAddOrUpdate",method = RequestMethod.GET)
		public JsonRs select(Role role, @RequestParam("addArry") String addArry, @RequestParam("deletaArry") String deletaArry) throws Exception {
				try {
						roleMenuService.addOrUpdate(role,addArry,deletaArry);
						return new JsonRs(2000,true,"操作成功!");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"操作失败!");
				}
		}
}
