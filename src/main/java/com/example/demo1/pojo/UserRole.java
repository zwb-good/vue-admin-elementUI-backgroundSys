package com.example.demo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 张文彬
 * @date 2020/7/15 12:00
 */
public class UserRole {
		private Integer id;
		private Integer roleId;
		private Integer userId;
		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date effectiveDate;

		@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date expirationDate;

		public Integer getId() {
				return id;
		}

		public void setId(Integer id) {
				this.id = id;
		}

		public Integer getRoleId() {
				return roleId;
		}

		public void setRoleId(Integer roleId) {
				this.roleId = roleId;
		}

		public Integer getUserId() {
				return userId;
		}

		public void setUserId(Integer userId) {
				this.userId = userId;
		}

		public Date getEffectiveDate() {
				return effectiveDate;
		}

		public void setEffectiveDate(Date effectiveDate) {
				this.effectiveDate = effectiveDate;
		}

		public Date getExpirationDate() {
				return expirationDate;
		}

		public void setExpirationDate(Date expirationDate) {
				this.expirationDate = expirationDate;
		}
}
