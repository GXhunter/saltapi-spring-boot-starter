package com.github.gxhunter.saltapi.service.impl;

import com.github.gxhunter.saltapi.cache.ILoginCache;
import com.github.gxhunter.saltapi.dto.response.BaseSaltApiResponse;
import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import com.github.gxhunter.saltapi.entity.SaltApiInfo;
import com.github.gxhunter.saltapi.dto.response.AsyncExecResponse;
import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.service.ISaltStackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @author 树荫下的天空
 * @date 2018/8/1 22:09
 * 在yml配置了saltapi信息时 注入此类
 */
@Slf4j
public class DefaultSaltStackServiceImpl implements ISaltStackService{
    private SaltApiInfo mSaltApiInfo;
    private SaltStackServiceImpl mStackService;

    /**
     *
     * @param restTemplate rest请求
     * @param saltApiInfo  在yml配置salt信息
     * @param loginResponseManage   缓存管理
     */
    public DefaultSaltStackServiceImpl(RestTemplate restTemplate,SaltApiInfo saltApiInfo,ILoginCache loginResponseManage){
        mStackService = new SaltStackServiceImpl(restTemplate,loginResponseManage);
        mSaltApiInfo = saltApiInfo;
    }

    @Override
    public LoginResponse login(){
        return mStackService.login(mSaltApiInfo);
    }

    @Override
    public AsyncExecResponse asyncExec(SaltApiExecRequest reqParam){
        return mStackService.asyncExec(mSaltApiInfo,reqParam);
    }

    @Override
    public <T extends BaseSaltApiResponse> T exec(SaltApiExecRequest request,Class<T> clazz){
        return mStackService.exec(mSaltApiInfo,request,clazz);
    }
}
