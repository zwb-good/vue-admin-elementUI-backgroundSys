package com.example.demo1.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张文彬
 * @date 2020/8/5 20:21
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

		@Override
		public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
				//返回json形式的错误信息
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setContentType("application/json");
				PrintWriter writer = httpServletResponse.getWriter();
				JSONObject res = new JSONObject();
				res.put("success", "false");
				res.put("msg", "权限不足");
				res.put("code", "5001");
				writer.write(res.toString());
				writer.flush();
				writer.close();
		}
}
