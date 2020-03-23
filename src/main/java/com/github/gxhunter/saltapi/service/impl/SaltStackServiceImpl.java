package com.github.gxhunter.saltapi.service.impl;

import com.github.gxhunter.saltapi.SaltConstant;
import com.github.gxhunter.saltapi.cache.ILoginCache;
import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.dto.response.AsyncExecResponse;
import com.github.gxhunter.saltapi.dto.response.BaseSaltApiResponse;
import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import com.github.gxhunter.saltapi.entity.SaltApiInfo;
import com.github.gxhunter.saltapi.enums.ClientType;
import com.github.gxhunter.saltapi.service.ISaltStackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/**
 * @author 树荫下的天空
 * @date 2018/11/28 17:48
 * 没有在yml注入salt信息，注入此类
 */
@Slf4j
public class SaltStackServiceImpl implements ISaltStackService, SaltConstant {
    private RestTemplate mRestTemplate;
    private ILoginCache mLoginResponseManage;

    public SaltStackServiceImpl(RestTemplate restTemplate,ILoginCache loginResponseManage){
        mRestTemplate = restTemplate;
        mLoginResponseManage = loginResponseManage;
    }

    @Override
    public LoginResponse login(SaltApiInfo saltApiInfo){
        String url = String.format("http://%s:%s/login",saltApiInfo.getIp(),saltApiInfo.getPort());
        Map<String, String> param = new HashMap<>();
        param.put(ParamKey.USERNAME,saltApiInfo.getUsername());
        param.put(ParamKey.PASSWORD,saltApiInfo.getPassword());
        param.put(ParamKey.EAUTH,"pam");
        LoginResponse loginResponse = mRestTemplate.postForObject(url,param,LoginResponse.class);
        mLoginResponseManage.setLoginResponse(loginResponse);
        return loginResponse;
    }

    @Override
    public AsyncExecResponse asyncExec(SaltApiInfo saltApiInfo,SaltApiExecRequest reqParam){
        reqParam.setClient(ClientType.CLIENT_LOCAL_ASYNC);
        return exec(saltApiInfo,reqParam,AsyncExecResponse.class);
    }

    @Override
    public <T extends BaseSaltApiResponse> T exec(SaltApiInfo saltApiInfo,SaltApiExecRequest request,Class<T> clazz){
        if(request.getClient() == null){
            request.setClient(ClientType.CLIENT_LOCAL);
        }
        final String url = String.format("http://%s:%s",saltApiInfo.getIp(),saltApiInfo.getPort());
        String token = mLoginResponseManage.getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeaderKey.XAUTH_TOKEN,token);
        headers.set(HeaderKey.ACCEPT,"application/json");
        HttpEntity httpEntity = new HttpEntity<>(request,headers);
        log.debug("--------saltapi相关参数----------");
        log.debug(String.format("请求地址:%s",url));
        log.debug("请求token：");
        log.debug(token);
        log.debug("请求参数：");
        log.debug(request.toString());
        log.debug("--------------------------------");
        return mRestTemplate
                .exchange(url,HttpMethod.POST,httpEntity,clazz).getBody();
    }
}
