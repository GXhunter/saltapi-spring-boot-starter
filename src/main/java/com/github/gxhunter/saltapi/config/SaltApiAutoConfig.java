package com.github.gxhunter.saltapi.config;

import com.github.gxhunter.saltapi.cache.ILoginCache;
import com.github.gxhunter.saltapi.cache.RedisLoginCacheImpl;
import com.github.gxhunter.saltapi.cache.SimpleLoginCacheImpl;
import com.github.gxhunter.saltapi.entity.SaltApiInfo;
import com.github.gxhunter.saltapi.service.ISaltStackService;
import com.github.gxhunter.saltapi.service.impl.DefaultSaltStackServiceImpl;
import com.github.gxhunter.saltapi.service.impl.SaltStackServiceImpl;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
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


    /**
     * 项目有redis
     *
     * @return
     */
    @Configuration
    @ConditionalOnClass(RedisTemplate.class)
    class WithRedis{
        /**
         * 优先使用redis存储登录数据
         *
         * @param redisTemplate
         * @return
         */
        @ConditionalOnBean(RedisTemplate.class)
        @Bean
        ILoginCache redisLoginCache(RedisTemplate redisTemplate){
            return new RedisLoginCacheImpl(redisTemplate);
        }
    }

    @Bean
    @ConditionalOnMissingBean({RedisTemplate.class,ILoginCache.class})
    ILoginCache simpleLoginCache(){
        return new SimpleLoginCacheImpl();
    }

    /**
     * 在yml配置了saltapi信息
     */
    @Bean
    @ConditionalOnBean(SaltApiInfo.class)
    ISaltStackService defaultSaltStackService(RestTemplate restTemplate,SaltApiInfo saltApiInfo,ILoginCache loginCache){
        return new DefaultSaltStackServiceImpl(restTemplate,saltApiInfo,loginCache);
    }

    /**
     * 在yml没有配置salt
     */
    @Bean
    @ConditionalOnMissingBean({ISaltStackService.class,SaltApiInfo.class})
    ISaltStackService saltStackService(RestTemplate restTemplate,ILoginCache loginCache){
        return new SaltStackServiceImpl(restTemplate,loginCache);
    }

}
