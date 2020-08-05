package com.example.demo1.service;

import com.example.demo1.dao.UserMapper;
import com.example.demo1.dao.UserRoleMapper;
import com.example.demo1.pojo.User;
import com.example.demo1.pojo.UserRole;
import com.example.demo1.vo.ChangeUserRoleVO;
import com.example.demo1.vo.UserVO;
import com.example.demo1.utils.JsonRs;
import com.example.demo1.utils.JwtTokenUtil;
import com.example.demo1.utils.Utils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
		@Resource
		UserMapper userMapper;
		@Resource
		SqlSessionFactory sqlSessionFactory;
		@Resource
		UserRoleMapper userRoleMapper;
		@Autowired
		private JwtTokenUtil jwtTokenUtil;
		@Autowired
		private AuthenticationManager authenticationManager;
		@Resource
		private HttpServletRequest request;

		/**
		 * 获取用户列表数据
		 *
		 * @param userVO
		 * @return
		 */
		public List<User> selectUser(UserVO userVO) {
				return userMapper.selectUser(userVO);
		}

		/**
		 * 统计用户个数
		 *
		 * @param userVO
		 * @return
		 */
		public int count(UserVO userVO) {
				return userMapper.count(userVO);
		}

		/**
		 * 判断用户是否存在
		 *
		 * @param username
		 * @return
		 */
		public Integer userSelectExist(String username) {
				return userMapper.userSelectExist(username);
		}

		/**
		 * 更新用户信息
		 *
		 * @param userVO
		 */
		public void updateUser(UserVO userVO) {
				userVO.setUpdateDate(new Date());
				userMapper.updateUser(userVO);
		}

		/**
		 * 删除用户角色，同时删除该用户拥有的角色联系
		 *
		 * @param ids
		 */
		@Transactional(rollbackFor = Exception.class)
		public void delete(Integer[] ids) {
				try {
						//删除用户
						userMapper.deleteUser(ids);
						//删除用户所拥有的角色联系
						userRoleMapper.deleteByUserId(ids);
				} catch (Exception e) {
						e.printStackTrace();
						throw e;
				}
		}

		/**
		 * 保存用户信息
		 *
		 * @param user
		 */
		public void saveUser(User user) {
				user.setCreateDate(new Date());
				user.setUpdateDate(new Date());
				userMapper.saveUser(user);
		}

		/**
		 * 登录
		 *
		 * @param username
		 * @param password
		 * @return
		 */
		public User login(String username, String password) {
				//根据用户名查找用户
				return loadUserByUsername(username);
		}

		public List<UserRole> selectRoleByUserId(Integer userId) throws Exception {
				return userRoleMapper.selectUserRole(userId);
		}

		/**
		 * 更改用户拥有的角色联系
		 *
		 * @param data
		 */
		@Transactional(rollbackFor = Exception.class)
		public void updateUserRole(ChangeUserRoleVO data) {
				// 要新加的用户角色信息不为空就添加用户和角色关系
				if (data.getAddRoleRows().size() != 0 && !data.getAddRoleRows().isEmpty()) {
						userRoleMapper.saveUserRole(data.getAddRoleRows(), data.getUserId());
				}
				// 要删除的用户角色id不为空就删除用户和角色关系
				// 根据角色id字符串和用户id删除
				if (data.getDeleteRoleIds().length() != 0) {
						userRoleMapper.deleteByRoleIdAndUserId(Utils.toChange(data.getDeleteRoleIds()), data.getUserId());
				}
				// 待修改的用户和角色关系数据
				if (data.getUpdateRoleRows().size() != 0 && !data.getUpdateRoleRows().isEmpty()) {
						userRoleMapper.updateUserRole(data.getUpdateRoleRows(), data.getUserId());
				}
		}

		@Override
		public User loadUserByUsername(String s) throws UsernameNotFoundException {
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute("token_" + s);
				if (user == null) {
						user = userMapper.findByUsername(s);
						if (user == null) {
								throw new UsernameNotFoundException("用户不存在");
						}
						session.setAttribute("token_" + s, user);
				}
				return user;
		}
}
