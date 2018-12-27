package com.github.gxhunter.saltapi.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2018/6/20 16:21
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class LoginResponse extends BaseSaltApiResponse<LoginResponse.ReturnX>{
    /**
     * return格式 : List<ReturnX>
     */

    @Data
    @NoArgsConstructor
    public static class ReturnX{
        /**
         * token获取时间
         */
        private Double start;
        /**
         * token过期时间，时间戳 单位秒（小数点 毫秒）
         */
        private Double expire;
        private String token;
        private String user;
        private String eauth;
        private List<String> perms;
    }

    public String getToken(){
        if(!CollectionUtils.isEmpty(getReturnX())){
            return getReturnX().get(0).getToken();
        }else {
            return null;
        }
    }
}
