package com.example.demo1.vo;

import com.example.demo1.pojo.Role;

import java.util.List;

/**
 * @author 张文彬
 * @date 2020/7/27 16:54
 */
public class ChangeUserRoleVO {
		private Integer userId;
		private String deleteRoleIds;
		private List<Role> addRoleRows;
		private List<Role> updateRoleRows;

		public Integer getUserId() {
				return userId;
		}

		public void setUserId(Integer userId) {
				this.userId = userId;
		}

		public String getDeleteRoleIds() {
				return deleteRoleIds;
		}

		public void setDeleteRoleIds(String deleteRoleIds) {
				this.deleteRoleIds = deleteRoleIds;
		}

		public List<Role> getAddRoleRows() {
				return addRoleRows;
		}

		public void setAddRoleRows(List<Role> addRoleRows) {
				this.addRoleRows = addRoleRows;
		}

		public List<Role> getUpdateRoleRows() {
				return updateRoleRows;
		}

		public void setUpdateRoleRows(List<Role> updateRoleRows) {
				this.updateRoleRows = updateRoleRows;
		}
}
