package com.example.demo1.service;

import com.example.demo1.dao.RoleMapper;
import com.example.demo1.dao.RoleMenuMapper;
import com.example.demo1.pojo.Role;
import com.example.demo1.vo.RoleVO;
import com.example.demo1.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
		@Resource
		RoleMapper roleMapper;
		@Resource
		RoleMenuMapper roleMenuMapper;

		public List<Role> selectRole(RoleVO roleVO) throws Exception{
				return roleMapper.selectRole(roleVO);
		}

		public int countRole(RoleVO roleVO) {
				return roleMapper.countRole(roleVO);
		}

		@Transactional(rollbackFor = Exception.class)
		public void saveRole(Role role) {
				try {
						roleMapper.saveRole(role);
				} catch (Exception e) {
						e.printStackTrace();
						throw e;
				}
		}

		public int updateRole(Role role) {
				return roleMapper.updateRole(role);
		}

		@Transactional(rollbackFor = Exception.class)
		public void deleteRole(Integer[] ids) {
				try {
						roleMapper.deleteRole(ids);
						roleMenuMapper.deleteByRoleId(ids);
				} catch (Exception e) {
						e.printStackTrace();
						throw e;
				}
		}

		public void updateRoleMenu(Integer roleId, String addMenuIds, String deleteMenuIds) {
				if(addMenuIds != ""){
						roleMenuMapper.saveRoleMenu(roleId,Utils.toChange(addMenuIds));
				}
				if(deleteMenuIds != ""){
						roleMenuMapper.deleteByRoleIdAndMenuIds(roleId,Utils.toChange(deleteMenuIds));
				}
		}
}
