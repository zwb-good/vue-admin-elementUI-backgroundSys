package com.example.demo1.vo;

import java.util.Date;

/**
 * @author 张文彬
 * @date 2020/7/22 9:19
 */
public class RoleVO extends BaseVO {
		private Integer id;

		private String code;

		private String name;

		private Date effectiveDate;

		private Date expirationDate;

		private Integer state;

		private Boolean haveEffective = false;

		public Integer getId() {
				return id;
		}

		public void setId(Integer id) {
				this.id = id;
		}

		public Integer getState() {
				return state;
		}

		public void setState(Integer state) {
				this.state = state;
		}

		public String getCode() {
				return code;
		}

		public void setCode(String code) {
				this.code = code;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
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

		public Boolean getHaveEffective() {
				return haveEffective;
		}

		public void setHaveEffective(Boolean haveEffective) {
				this.haveEffective = haveEffective;
		}
}
