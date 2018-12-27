package com.github.gxhunter.saltapi.enums;

/**
 * @author 树荫下的天空
 * @date 2018/11/4 0:43
 * 后续扩展模块，实现此接口即可
 */
public interface ISaltModule{
    /**
     * salt模块名称
     *
     * @return
     */
    String getModuleName();

    /**
     * 模块的执行方式
     *
     * @return
     */
    String getClient();
}
