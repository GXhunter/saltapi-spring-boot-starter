package com.github.gxhunter.saltapi;

/**
 * @author 树荫下的天空
 * @date 2018/7/31 9:31
 */
public interface SaltConstant{
    /**
     * 参数key值
     */
    interface ParamKey{
        String USERNAME = "username";
        String PASSWORD = "password";
        String EAUTH = "eauth";
    }

    /**
     * 请求头key值
     */
    interface HeaderKey{
        String ACCEPT = "Accept";
        String XAUTH_TOKEN = "X-Auth-Token";
    }

    /**
     * tgt匹配规则（执行补充）
     */
    interface ExprForm{
        /**
         * 列表形式
         */
        String LIST = "list";
        /**
         * 默认形式
         */
        String DEFAULT = "glob";
    }

    /**
     * 执行方式
     */
    interface ClientType{
        /**
         * 同步阻塞
         */
        String CLIENT_LOCAL = "local";
        /**
         * 异步方式
         */
        String CLIENT_LOCAL_ASYNC = "local_async";
        String CLIENT_RUNNER = "runner";
    }
}
