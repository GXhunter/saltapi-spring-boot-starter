package com.github.gxhunter.saltapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.github.gxhunter.saltapi.SaltConstant.ClientType.*;
/**
 * @author 树荫下的天空
 * @date 2018/10/11 14:46
 * SaltStack常见的内置模块，可自行继承{@link ISaltModule}扩展补充
 *
 */
@Getter
@AllArgsConstructor
public enum SimpleSaltModule implements ISaltModule{
    /**
     * 测试被控机的连通性
     */
    TEST_PING("test.ping",CLIENT_LOCAL),
    /**
     * 删除job
     */
    KILL_JOB("saltutil.kill_job",CLIENT_LOCAL),
    /**
     * 所有运行中job
     */
    RUNNING_JOB("saltutil.running",CLIENT_LOCAL),
    /**
     * 查找指定job
     */
    JOB_DETAIL("saltutil.find_job",CLIENT_LOCAL),
    /**
     * 分发自定义模块到被控机
     */
    MODULE_SYNC("saltutil.sync_modules",CLIENT_LOCAL),

    /**
     * 查看被控机上线状态
     */
    MINION_STATUS("manage.status",CLIENT_RUNNER),

    /**
     * 执行Linux shell命令
     */
    SHELL("cmd.run",CLIENT_LOCAL),
    ;
    private String moduleName;
    private String client;
}
