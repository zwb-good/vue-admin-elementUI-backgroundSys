package com.example.demo1;

import com.example.demo1.comm.Const;
import com.example.demo1.dao.MenuMapper;
import com.example.demo1.dao.RoleMenuMapper;
import com.example.demo1.dao.UserRoleMapper;
import com.example.demo1.pojo.Menu;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import com.example.demo1.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Demo1ApplicationTests {
    @Resource
    UserService userService;
    @Resource
    MenuMapper menuMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() throws Exception {
        System.out.println(userService.login("20160202638", "123456"));
    }

    @Test
    void test() throws Exception {
        List<Menu> rootMenu = menuMapper.selectMenu(null);
        // 构建好的菜单树，第一层菜单的pid是null
        List<Menu> menuTree = buildMenuTree(rootMenu, null);
        System.out.println(menuTree);
    }

    private List<Menu> buildMenuTree(List<Menu> menuList, Integer pid) {
        List<Menu> treeList = new ArrayList<>();
        menuList.forEach(menu -> {
            if (pid == menu.getParentId()) {
                menu.setChildren(buildMenuTree(menuList, menu.getId()));
                treeList.add(menu);
            }
        });
        return treeList;
    }


    @Test
    void isPalindrome() throws Exception {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1OTYwNjc0Nzk3NzgsImV4cCI6MjAyODA2NzQ3OX0.DFsHV2s89NwjVHH8eTqRtx4cuVhIXSOODPLYblaiiTydJJQR5FDWWJomgFn9-5q3ll6fvqeZY21BgvpwA3I47w";
        System.out.println(token);
        String authToken = token.substring( Const.TOKEN_PREFIX.length() );
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        System.out.println(username);
        /*BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        String str = "123456";
        System.out.println(bcp.encode(str));*/
    }

}
