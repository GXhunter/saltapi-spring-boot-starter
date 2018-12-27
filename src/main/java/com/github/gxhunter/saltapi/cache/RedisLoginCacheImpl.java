package com.github.gxhunter.saltapi.cache;

import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 树荫下的天空
 * @date 2018/11/28 16:52
 */
public class RedisLoginCacheImpl implements ILoginCache{
    /**
     * 登录返回值存在redis时的key
     */
    private static final String LOGIN_RESP_KEY = "login_resp_key";
    private RedisTemplate<String, LoginResponse> redisTemplate;

    public RedisLoginCacheImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public LoginResponse getLoginResponse(){
        return redisTemplate.opsForValue().get(LOGIN_RESP_KEY);
    }

    @Override
    public void setLoginResponse(LoginResponse loginResponse){
        long timeout = (long) (loginResponse.getReturnOne().getExpire() - loginResponse.getReturnOne().getStart());
        redisTemplate.opsForValue().set(LOGIN_RESP_KEY,loginResponse,timeout,TimeUnit.SECONDS);
    }
}
