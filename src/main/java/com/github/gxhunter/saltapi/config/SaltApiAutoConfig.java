package com.github.gxhunter.saltapi.config;

import com.github.gxhunter.saltapi.properties.SaltApiProperties;
import com.github.gxhunter.saltapi.service.ISaltStackClient;
import com.github.gxhunter.saltapi.service.impl.SaltStackClientImpl;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 树荫下的天空
 * @date 2018/8/13 15:18
 */
@Configuration
@Data
@SuppressWarnings("all")
public class SaltApiAutoConfig{

    /**
     * 没有提供 restTemplate时
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    @ConditionalOnClass(RestTemplate.class)
    RestTemplate restTemplate(){
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setReadTimeout(15000);
        factory.setConnectTimeout(5000);
        factory.setWriteTimeout(30000);
        return new RestTemplate(factory);
    }



    @Bean
    @ConditionalOnMissingBean({ISaltStackClient.class, SaltApiProperties.class})
    ISaltStackClient saltStackService(RestTemplate restTemplate, SaltApiProperties properties){
        return new SaltStackClientImpl(restTemplate,properties);
    }

}
