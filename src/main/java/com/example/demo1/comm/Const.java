package com.example.demo1.comm;

/**
 * @www.codesheep.cn
 * 20190312
 */
public class Const {

    public static final long EXPIRATION_TIME = 1000*60*30;     // 1天(以毫秒ms计)
    public static final long REMEBER_TIME = 24*3600*1000*5;     // 5天(以毫秒ms计)
    public static final String SECRET = "CodeSheepSecret";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
