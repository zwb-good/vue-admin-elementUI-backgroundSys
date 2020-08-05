package com.example.demo1.utils;

import com.example.demo1.pojo.Menu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
		public static String getMd5(String inStr) {
				String outStr = null;
				if (inStr == null) {
						outStr = null;
				} else if ("".equals(inStr)) {
						outStr = "";
				} else {
						try {
								MessageDigest md = MessageDigest.getInstance("MD5");
								md.update(inStr.getBytes());
								byte[] b = md.digest();
								StringBuffer buf = new StringBuffer();
								for (int i = 1; i < b.length; i++) {
										int c = b[i] >>> 4 & 0xf;
										buf.append(Integer.toHexString(c));
										c = b[i] & 0xf;
										buf.append(Integer.toHexString(c));
								}
								outStr = buf.toString();
						} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
				}
				return outStr;
		}

		public static List<Menu> buildMenuTree(List<Menu> menuList, Integer pid) {
				List<Menu> treeList = new ArrayList<>();
				menuList.forEach(menu -> {
						if (pid.equals(menu.getParentId())) {
								menu.setChildren(buildMenuTree(menuList, menu.getId()));
								treeList.add(menu);
						}
				});
				return treeList;
		}

		public static Integer[] toChange(String str) {
				String[] strArr = str.split(",");
				Integer[] intArr = new Integer[strArr.length];
				for(int i=0;i<strArr.length;i++){
						intArr[i] = Integer.valueOf(strArr[i]);
				}
				return intArr;
		}

		public static void main(String[] args) {
				String ss = Utils.getMd5("123456");
				System.err.println(ss);
		}
}
