package com.github.gxhunter.saltapi;

/**
 * @author 树荫下的天空
 * @date 2018/7/31 9:31
 */
public interface SaltConstant{
    /**
     * 参数key值
     */
    interface ParamKey{
        String USERNAME = "username";
        String PASSWORD = "password";
        String EAUTH = "eauth";
    }

    /**
     * 请求头key值
     */
    interface HeaderKey{
        String ACCEPT = "Accept";
        String XAUTH_TOKEN = "X-Auth-Token";
    }

}
