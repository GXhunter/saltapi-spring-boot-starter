package com.github.gxhunter.saltapi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 树荫下的天空
 * @date 2018/9/29 9:44
 * saltapi相关信息
 */
@Data
@ConfigurationProperties(prefix = "hunter.spring.saltapi")
@Component
public class SaltApiProperties {
    /**
     * 主控机ip
     */
    private String ip;
    /**
     * 主控机端口
     */
    private Integer port;
    /**
     * saltapi用户名
     */
    private String username;
    /**
     * saltapi密码
     */
    private String password;

}
