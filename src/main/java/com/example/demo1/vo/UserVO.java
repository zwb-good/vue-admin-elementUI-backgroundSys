package com.example.demo1.vo;

import com.example.demo1.pojo.Menu;
import com.example.demo1.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserVO extends BaseVO {
		private Integer id;

		private String username;

		private String password;

		private String name;

		private String sex;

		private String phone;

		private String email;

		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date createDate;

		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date updateDate;

		private Integer state;

		private Integer isDel;

		private List<Menu> menuList;

		private String ids;

		private String addRoles;

		private String deleteRoles;

		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date startDate;

		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date endDate;

		private Integer[] roleIds;

		public String getDeleteRoles() {
				return deleteRoles;
		}

		public void setDeleteRoles(String deleteRoles) {
				this.deleteRoles = deleteRoles;
		}

		public String getIds() {
				return ids;
		}

		public void setIds(String ids) {
				this.ids = ids;
		}

		public String getAddRoles() {
				return addRoles;
		}

		public void setAddRoles(String addRoles) {
				this.addRoles = addRoles;
		}

		public Integer[] getRoleIds() {
				return roleIds;
		}

		public void setRoleIds(String roleIds) {
				if("".equals(roleIds)){
						this.roleIds = new Integer[]{};
				}else{
						this.roleIds = Utils.toChange(roleIds);
				}
		}

		public Integer getId() {
				return id;
		}

		public void setId(Integer id) {
				this.id = id;
		}

		public String getEmail() {
				return email;
		}

		public void setEmail(String email) {
				this.email = email;
		}

		public String getPassword() {
				return password;
		}

		public void setPassword(String password) {
				this.password = password;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				if(name == "" || name == "null"){
						this.name = null;
				}else{
						this.name = name.trim();
				}
		}

		public String getSex() {
				return sex;
		}

		public void setSex(String sex) {
				this.sex = sex;
		}

		public Integer getIsDel() {
				return isDel;
		}

		public void setIsDel(Integer isDel) {
				this.isDel = isDel;
		}

		public Date getCreateDate() {
				return createDate;
		}

		public void setCreateDate(Date createDate) {
				this.createDate = createDate;
		}

		public Integer getState() {
				return state;
		}

		public void setState(Integer state) {
				this.state = state;
		}

		public List<Menu> getMenuList() {
				return menuList;
		}

		public void setMenuList(List<Menu> menuList) {
				this.menuList = menuList;
		}

		public Date getStartDate() {
				return startDate;
		}

		public void setStartDate(Date startDate) {
				this.startDate = startDate;
		}

		public Date getEndDate() {
				return endDate;
		}

		public void setEndDate(Date endDate) {
				this.endDate = endDate;
		}

		public String getUsername() {
				return username;
		}

		public void setUsername(String username) {
				this.username = username;
		}

		public String getPhone() {
				return phone;
		}

		public void setPhone(String phone) {
				this.phone = phone;
		}

		public Date getUpdateDate() {
				return updateDate;
		}

		public void setUpdateDate(Date updateDate) {
				this.updateDate = updateDate;
		}
}
