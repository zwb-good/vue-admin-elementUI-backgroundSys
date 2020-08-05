package com.example.demo1.vo;

import org.springframework.util.StringUtils;

public class BaseVO {
		protected Integer page;
		protected Integer rows;
		protected Integer pageSize;

		protected Integer recordIndex;
		protected String orderBy;
		protected String order;
		protected String condition;

		public Integer getPage() {
				return page;
		}

		public Integer getRecordIndex() {
				if (page != null && rows != null) {
						recordIndex = (page - 1) * rows;
				}
				return recordIndex;
		}

		public String getOrderBy() {
				return orderBy;
		}

		public String getCondition() {
				if (StringUtils.isEmpty(condition)) {
						return null;
				} else {
						return condition.replaceAll("@", "'");
				}
		}

		public void setPage(Integer page) {
				this.page = page;
		}

		public void setRecordIndex(Integer recordIndex) {
				this.recordIndex = recordIndex;
		}

		public void setOrderBy(String orderBy) {
				this.orderBy = orderBy;
		}

		public void setCondition(String condition) {
				this.condition = condition;
		}

		public Integer getRows() {
				return rows;
		}

		public void setRows(Integer rows) {
				this.rows = rows;
		}

		public Integer getPageSize() {
				return rows;
		}

		public void setPageSize(Integer pageSize) {
				this.pageSize = pageSize;
		}

		public String getOrder() {
				return order;
		}

		public void setOrder(String order) {
				this.order = order;
		}
}
