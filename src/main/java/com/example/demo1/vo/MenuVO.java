package com.example.demo1.vo;

import com.example.demo1.pojo.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuVO extends BaseVO {
		private Integer id;

		private String title;

		private String type;

		private String icon;

		private Integer parentId;

		private Integer index;

		private Integer state;

		private String mark;

		private String component;

		private List<Menu> children = new ArrayList<>();

		private Boolean noFunctionMenu;

		public Integer getId() {
				return id;
		}

		public void setId(Integer id) {
				this.id = id;
		}

		public String getTitle() {
				return title;
		}

		public void setTitle(String title) {
				this.title = title;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}

		public String getIcon() {
				return icon;
		}

		public void setIcon(String icon) {
				this.icon = icon;
		}

		public Integer getParentId() {
				return parentId;
		}

		public void setParentId(Integer parentId) {
				this.parentId = parentId;
		}

		public Integer getIndex() {
				return index;
		}

		public void setIndex(Integer index) {
				this.index = index;
		}

		public Integer getState() {
				return state;
		}

		public void setState(Integer state) {
				this.state = state;
		}

		public String getMark() {
				return mark;
		}

		public void setMark(String mark) {
				this.mark = mark;
		}

		public String getComponent() {
				return component;
		}

		public void setComponent(String component) {
				this.component = component;
		}

		public List<Menu> getChildren() {
				return children;
		}

		public void setChildren(List<Menu> children) {
				this.children = children;
		}

		public Boolean isNoFunctionMenu() {
				return noFunctionMenu;
		}

		public void setNoFunctionMenu(Boolean noFunctionMenu) {
				this.noFunctionMenu = noFunctionMenu;
		}
}
