package com.example.demo1.controller;

import com.example.demo1.comm.Const;
import com.example.demo1.pojo.User;
import com.example.demo1.pojo.UserRole;
import com.example.demo1.vo.ChangeUserRoleVO;
import com.example.demo1.vo.UserVO;
import com.example.demo1.service.UserService;
import com.example.demo1.utils.JsonRs;
import com.example.demo1.utils.JwtTokenUtil;
import com.example.demo1.utils.Utils;
import com.ramostear.captcha.HappyCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 张文彬
 * @date 2020/7/15 10:46
 */
@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		UserService userService;
		@Autowired
		private JwtTokenUtil jwtTokenUtil;

		/**
		 * 登录获取token
		 *
		 * @param username   用户名
		 * @param password   密码
		 * @param verifyCode 验证码
		 * @param request
		 * @return JsonRs
		 */
		@ResponseBody
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public JsonRs login(@RequestParam("username") String username,
												@RequestParam("password") String password,
												@RequestParam("code") String verifyCode,
												@RequestParam("rememberMe") Boolean rememberMe,HttpServletRequest request) {
				boolean flag = HappyCaptcha.verification(request, verifyCode, true);
				if (flag) {
						HappyCaptcha.remove(request);
						User user = userService.login(username, password);
						if(user == null){
								return new JsonRs(2000,false,"该用户不存在");
						}else{
								//比较加密密码是否一致
								BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
								if(!bcp.matches(password,user.getPassword())){
										return new JsonRs(2000,false,"密码错误");
								}
								//判断账号是否有效
								if(user.getState() == 0){
										return new JsonRs(2000,false,"账号无效，请联系管理员");
								}
								final String token = jwtTokenUtil.generateToken(user,rememberMe);
								return new JsonRs(2000,true,"登陆成功",1,token);
						}

				} else {
						return new JsonRs(2000,false,"验证码错误");
				}
		}

		/**
		 * 根据token获取登录者信息
		 *
		 * @param token
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
		public JsonRs getInfo(@RequestParam("token") String token) {
				if (token.length() != 0 && token.startsWith(Const.TOKEN_PREFIX)) {
						token = token.substring(Const.TOKEN_PREFIX.length());
						String username = jwtTokenUtil.getUsernameFromToken(token);
						User user = userService.loadUserByUsername(username);
						return new JsonRs(2000,true,"查询成功", 1, user);
				} else {
						return new JsonRs(2001,false,"查询失败");
				}
		}

		/**
		 * 获取验证码接口
		 *
		 * @param reqeust
		 * @param response
		 */
		@GetMapping("/captcha")
		public void happyCaptcha(HttpServletRequest reqeust, HttpServletResponse response) {
				HappyCaptcha.require(reqeust, response).length(4).build().finish();
		}

		/**
		 * 查询用户列表list
		 *
		 * @param userVO：查询条件
		 * @return
		 */
		@RequestMapping(value = "/selectUser", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs selectUser(UserVO userVO) {
				List<User> list = null;
				int total = userService.count(userVO);
				list = userService.selectUser(userVO);
				return new JsonRs(2000,true,"查询成功", total, list);
		}

		/**
		 * 新增用户保存
		 *
		 * @param user：新增用户信息
		 * @return
		 */
		@RequestMapping(value = "/saveUser", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs saveUser(User user) {
				try {
						if (userService.userSelectExist(user.getUsername()) != null) {
								return new JsonRs(2000,false,"用户名已注册");
						} else {
								BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
								user.setPassword(bcp.encode(user.getPassword()));
								userService.saveUser(user);
								return new JsonRs(2000,true,"添加成功");
						}
				} catch (Exception e) {
						return new JsonRs(2001,false,"操作失败");
				}
		}

		/**
		 * 修改用户信息
		 *
		 * @param userVO：要修改的用户信息
		 * @return
		 */
		@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs updateUser(UserVO userVO) {
				try {
						userService.updateUser(userVO);
						return new JsonRs(2000,true,"更新成功");
				} catch (Exception e) {
						e.getMessage();
						return new JsonRs(2001,false,"操作失败");
				}
		}

		/**
		 * 用户删除，支持批量删除
		 *
		 * @param ids：要删除的用户id字符串，用","分隔。例如（1,2,3）
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs deleteUser(@RequestParam("ids") String ids) throws Exception {
				try {
						userService.delete(Utils.toChange(ids));
						return new JsonRs(2000,true,"删除成功");
				} catch (Exception e) {
						return new JsonRs(2001,false,"操作失败");
				}
		}

		/**
		 * 根据用户id查找该用户拥有的角色
		 *
		 * @param userId：用户id
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/selectRoleByUserId", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs selectRoleByUserId(@RequestParam("userId") Integer userId) throws Exception {
				try {
						List<UserRole> list = userService.selectRoleByUserId(userId);
						return new JsonRs(2000,true,"查询成功", list.size(), list);
				} catch (Exception e) {
						return new JsonRs(2001,false,"查询失败");
				}
		}

		/**
		 * 更改某个用户拥有的角色
		 *
		 * @param data：接收修改用户角色的对象
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
		@ResponseBody
		public JsonRs updateUserRole(@RequestBody ChangeUserRoleVO data) throws Exception {
				try {
						userService.updateUserRole(data);
						return new JsonRs(2000,true,"更新成功");
				} catch (Exception e) {
						return new JsonRs(2001,false,"更新失败");
				}
		}

		/**
		 * 退出登录
		 * @param token：
		 * @return
		 */
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		@ResponseBody
		public JsonRs updateUserRole(@RequestParam("token") String token,HttpServletRequest request) {
				//获取用户名
				if (token.length() != 0 && token.startsWith(Const.TOKEN_PREFIX)) {
						token = token.substring(Const.TOKEN_PREFIX.length());
						String username = jwtTokenUtil.getUsernameFromToken(token);
						//把session里存的用户置空
						request.getSession().setAttribute("token_"+username,null);
						return new JsonRs(2000,true,"退出成功");
				}else{
						return new JsonRs(2001,false,"退出失败");
				}
		}
}
