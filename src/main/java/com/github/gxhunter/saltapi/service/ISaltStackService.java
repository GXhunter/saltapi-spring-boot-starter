package com.github.gxhunter.saltapi.service;

import com.github.gxhunter.saltapi.dto.response.AsyncExecResponse;
import com.github.gxhunter.saltapi.dto.response.BaseSaltApiResponse;
import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.entity.SaltApiInfo;

/**
 * @author 树荫下的天空
 * @date 2018/8/1 22:10
 */
public interface ISaltStackService{
    /**
     * 登录saltapi
     *
     * @return
     */
    default LoginResponse login(){
        throw new IllegalArgumentException("你没有在yml配置salt信息，请调用带salt参数的方法");
    }

    /**
     * 异步方式执行
     *
     * @param reqParam
     * @return
     */
    default AsyncExecResponse asyncExec(SaltApiExecRequest reqParam){
        throw new IllegalArgumentException("你没有在yml配置salt信息，请调用带salt参数的方法");
    }

    default <T extends BaseSaltApiResponse> T exec(SaltApiExecRequest request,Class<T> clazz){
        throw new IllegalArgumentException("你没有在yml配置salt信息，请调用带salt参数的方法");
    }

    /**
     * -----------当salt信息不在yml--------------
     */
    /**
     * 登录saltapi
     *
     * @return
     */
    default LoginResponse login(SaltApiInfo saltApiInfo){
        return login();
    }

    /**
     * 异步方式执行
     *
     * @param reqParam
     * @return
     */
    default AsyncExecResponse asyncExec(SaltApiInfo saltApiInfo,SaltApiExecRequest reqParam){
        return asyncExec(reqParam);
    }

    default <T extends BaseSaltApiResponse> T exec(SaltApiInfo saltApiInfo,SaltApiExecRequest request,Class<T> clazz){
        return exec(request,clazz);
    }
}
