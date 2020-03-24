package com.github.gxhunter.saltapi.service.impl;

import com.github.gxhunter.cache.Cache;
import com.github.gxhunter.saltapi.SaltConstant;
import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.dto.response.AsyncExecResponse;
import com.github.gxhunter.saltapi.dto.response.BaseSaltApiResponse;
import com.github.gxhunter.saltapi.dto.response.LoginResponse;
import com.github.gxhunter.saltapi.enums.ClientType;
import com.github.gxhunter.saltapi.properties.SaltApiProperties;
import com.github.gxhunter.saltapi.service.ISaltStackClient;
import com.github.gxhunter.util.ConstantValue;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SaltStackClientImpl implements ISaltStackClient, SaltConstant, ConstantValue.Spel {
    private final RestTemplate mRestTemplate;
    private final SaltApiProperties mSaltApiProperties;

    @Override
    @Cache(timeout = 720, prefix = Context.METHOD_NAME, key =
            "{#saltApiProperties.ip,#saltApiProperties.port,#saltApiProperties.username,#saltApiProperties.password}")
    public LoginResponse login(SaltApiProperties saltApiProperties) {
        String url = String.format("http://%s:%s/login", saltApiProperties.getIp(), saltApiProperties.getPort());
        Map<String, String> param = new HashMap<>();
        param.put(ParamKey.USERNAME, saltApiProperties.getUsername());
        param.put(ParamKey.PASSWORD, saltApiProperties.getPassword());
        param.put(ParamKey.EAUTH, "pam");
        return mRestTemplate.postForObject(url, param, LoginResponse.class);
    }

    @Override
    public AsyncExecResponse asyncExec(SaltApiProperties saltApiProperties, SaltApiExecRequest reqParam) {
        reqParam.setClient(ClientType.CLIENT_LOCAL_ASYNC);
        return exec(saltApiProperties, reqParam, AsyncExecResponse.class);
    }

    @Override
    public <T extends BaseSaltApiResponse> T exec(SaltApiProperties saltApiProperties, SaltApiExecRequest request, Class<T> clazz) {
        if (request.getClient() == null) {
            request.setClient(ClientType.CLIENT_LOCAL);
        }
        final String url = String.format("http://%s:%s", saltApiProperties.getIp(), saltApiProperties.getPort());
        String token = login().getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeaderKey.XAUTH_TOKEN, token);
        headers.set(HeaderKey.ACCEPT, "application/json");
        HttpEntity httpEntity = new HttpEntity<>(request, headers);
        log.debug("--------saltapi相关参数----------");
        log.debug(String.format("请求地址:%s", url));
        log.debug("请求token：");
        log.debug(token);
        log.debug("请求参数：");
        log.debug(request.toString());
        log.debug("--------------------------------");
        return mRestTemplate
                .exchange(url, HttpMethod.POST, httpEntity, clazz).getBody();
    }

    /**
     * 缓存30秒登录
     *
     * @return
     */
    @Override
    public LoginResponse login() {
        return login(mSaltApiProperties);
    }

    @Override
    public AsyncExecResponse asyncExec(SaltApiExecRequest reqParam) {
        return asyncExec(mSaltApiProperties, reqParam);
    }

    @Override
    public <T extends BaseSaltApiResponse> T exec(SaltApiExecRequest request, Class<T> clazz) {
        return exec(mSaltApiProperties, request, clazz);
    }
}
