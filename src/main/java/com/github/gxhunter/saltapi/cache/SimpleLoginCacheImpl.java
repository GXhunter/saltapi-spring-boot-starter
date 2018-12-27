package com.github.gxhunter.saltapi.cache;

import com.github.gxhunter.saltapi.dto.response.LoginResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author 树荫下的天空
 * @date 2018/11/28 17:07
 * 享元模式存储登录数据
 */
public class SimpleLoginCacheImpl implements ILoginCache{
    private static LoginResponse loginResponse;

    @Override
    public LoginResponse getLoginResponse(){
        LocalDateTime now = LocalDateTime.now();
        Instant instant = Instant.ofEpochMilli(loginResponse.getReturnOne().getExpire().longValue());
        LocalDateTime expireTime = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
        return now.isBefore(expireTime) ? loginResponse:null;
    }

    @Override
    public synchronized void setLoginResponse(LoginResponse loginResponse){
        SimpleLoginCacheImpl.loginResponse = loginResponse;
    }
}
