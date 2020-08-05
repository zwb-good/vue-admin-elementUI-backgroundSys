package com.example.demo1.service;

import com.example.demo1.dao.RoleMapper;
import com.example.demo1.dao.RoleMenuMapper;
import com.example.demo1.pojo.Role;
import com.example.demo1.pojo.RoleMenu;
import com.example.demo1.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuService {
		@Resource
		RoleMenuMapper roleMenuMapper;
		@Resource
		RoleMapper roleMapper;

		public List<RoleMenu> selectRoleMenu(RoleMenu roleMenu) throws Exception {
				return roleMenuMapper.selectRoleMenu(roleMenu);
		}

		@Transactional(rollbackFor = Exception.class)
		public void addOrUpdate(Role role, String addData, String deleteData) {
				if (!"".equals(deleteData)) {
						roleMenuMapper.deleteByRoleIdAndMenuIds(role.getId(), Utils.toChange(deleteData));
				}
				if (!"".equals(addData)) {
						roleMenuMapper.saveRoleMenu(role.getId(), Utils.toChange(addData));
				}
				roleMapper.saveRole(role);
		}

}
