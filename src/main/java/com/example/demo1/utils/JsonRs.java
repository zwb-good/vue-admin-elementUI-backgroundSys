package com.example.demo1.utils;

import java.util.ArrayList;
import java.util.List;

public class JsonRs {

		private Integer code;

		private Boolean success;

		private String msg;

		private Integer total;// 记录数

		private Object data;// 记录集

		public String getMsg() {
				return msg;
		}

		public void setMsg(String msg) {
				this.msg = msg;
		}

		public Boolean getSuccess() {
				return success;
		}

		public void setSuccess(Boolean success) {
				this.success = success;
		}

		public Integer getCode() {
				return code;
		}

		public void setCode(Integer code) {
				this.code = code;
		}

		public Integer getTotal() {
				return total;
		}

		public void setTotal(Integer total) {
				this.total = total;
		}

		public Object getData() {
				return data;
		}

		public void setData(Object data) {
				this.data = data;
		}

		public JsonRs() {
			super();
		}

		public JsonRs(Integer code, Boolean success, String msg) {
				this.code = code;
				this.success = success;
				this.msg = msg;
		}

		public JsonRs(Integer code, Boolean success, String msg, Integer total, Object data) {
				this.code = code;
				this.success = success;
				this.msg = msg;
				this.total = total;
				this.data = data;
		}
}
