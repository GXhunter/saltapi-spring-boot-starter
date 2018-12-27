package com.github.gxhunter.saltapi.cache;

import com.github.gxhunter.saltapi.dto.response.LoginResponse;

/**
 * @author 树荫下的天空
 * @date 2018/11/28 16:44
 * 登录缓存
 */
public interface ILoginCache{
    /**
     * 获取token
     * @return null没有获取到token
     */
    default String getToken(){
        return getLoginResponse().getToken();
    }


    /**
     * 缓存中的登录结果
     * @return null 没有对应的缓存
     */
    LoginResponse getLoginResponse();

    /**
     * 缓存登录结果，超时由内部决定
     * @param loginResponse
     * @return
     */
    void setLoginResponse(LoginResponse loginResponse);
}
