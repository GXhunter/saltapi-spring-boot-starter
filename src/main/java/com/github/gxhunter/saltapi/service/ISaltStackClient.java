package com.github.gxhunter.saltapi.service;

import com.github.gxhunter.saltapi.dto.response.AsyncExecResponse;
import com.github.gxhunter.saltapi.dto.response.BaseSaltApiResponse;
import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.properties.SaltApiProperties;

/**
 * @author 树荫下的天空
 * @date 2018/8/1 22:10
 */
public interface ISaltStackClient {
    /**
     * 登录saltapi
     *
     * @return
     */
     LoginResponse login();

    /**
     * 异步方式执行
     *
     * @param reqParam
     * @return
     */
     AsyncExecResponse asyncExec(SaltApiExecRequest reqParam);

     <T extends BaseSaltApiResponse> T exec(SaltApiExecRequest request,Class<T> clazz);
    /**
     * -----------当salt信息不在yml--------------
     */
    /**
     * 登录saltapi
     *
     * @return
     */
     LoginResponse login(SaltApiProperties saltApiProperties);
    /**
     * 异步方式执行
     *
     * @param reqParam
     * @return
     */
     AsyncExecResponse asyncExec(SaltApiProperties saltApiProperties, SaltApiExecRequest reqParam);

     <T extends BaseSaltApiResponse> T exec(SaltApiProperties saltApiProperties, SaltApiExecRequest request, Class<T> clazz);
}
