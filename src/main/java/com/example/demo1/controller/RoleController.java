package com.example.demo1.controller;

import com.example.demo1.pojo.Role;
import com.example.demo1.vo.RoleVO;
import com.example.demo1.service.RoleService;
import com.example.demo1.utils.JsonRs;
import com.example.demo1.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
		@Resource
		RoleService roleService;

		/**
		 * 角色列表查询
		 * @param roleVO 查询条件
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/selectRole")
		@ResponseBody
		public JsonRs selectRole(RoleVO roleVO) throws Exception{
				List<Role> list = null;
				list = roleService.selectRole(roleVO);
				int total = roleService.countRole(roleVO);
				return new JsonRs(2000,true,"查询成功",total,list);
		}

		/**
		 * 新添加角色信息
		 * @param role
		 * @return
		 */
		@RequestMapping("/saveRole")
		@ResponseBody
		public JsonRs saveRole(Role role) {
				try {
						roleService.saveRole(role);
						return new JsonRs(2000,true,"添加成功");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"添加失败");
				}
		}

		/**
		 * 更新角色信息
		 * @param role 要更改的角色信息
		 * @return
		 */
		@RequestMapping("/updateRole")
		@ResponseBody
		public JsonRs updateRole(Role role) {
				int total = 0;
				try {
						total = roleService.updateRole(role);
						if(total < 0) {
								return new JsonRs(2001,false,"更新失败");
						}else{
								return new JsonRs(2000,true,"更新成功");
						}
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"更新失败");
				}
		}

		/**
		 * 根据id字符串（“，”分隔）删除角色信息，可批量删除
		 * @param ids id字符串
		 * @return
		 */
		@RequestMapping("/deleteRole")
		@ResponseBody
		public JsonRs deleteRole(String ids) {
				try {
						// Utils.toChange() 把id字符串转成id数组
						roleService.deleteRole(Utils.toChange(ids));
						return new JsonRs(2000,true,"删除成功");
				} catch (Exception e) {
						e.printStackTrace();
						return new JsonRs(2001,false,"删除失败");
				}
		}

		/**
		 * 更新角色信息
		 * @param userId 角色id
		 * @param addMenuIds 该角色新添加的菜单权限id字符串
		 * @param deleteMenuIds 该角色要删除的菜单权限id字符串
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/updateRoleMenu")
		@ResponseBody
		public JsonRs updateRoleMenu(@RequestParam("roleId") Integer userId,
																 @RequestParam("addMenuIds") String addMenuIds,
																 @RequestParam("deleteMenuIds") String deleteMenuIds) throws Exception{
				try {
						roleService.updateRoleMenu(userId,addMenuIds,deleteMenuIds);
						return new JsonRs(2000,true,"更新成功");
				} catch (Exception e) {
						return new JsonRs(2001,false,"更新失败");
				}
		}

}
