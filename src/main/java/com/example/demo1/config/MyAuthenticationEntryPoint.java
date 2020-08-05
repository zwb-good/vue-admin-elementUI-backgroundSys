package com.example.demo1.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 张文彬
 * @date 2020/8/5 21:21
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
				httpServletResponse.setCharacterEncoding("UTF-8");
				httpServletResponse.setContentType("application/json");
				PrintWriter writer = httpServletResponse.getWriter();
				JSONObject res = new JSONObject();
				res.put("success", "false");
				res.put("msg", "迷路了哟！");
				res.put("code", "5001");
				writer.write(res.toString());
				writer.flush();
				writer.close();
		}
}
