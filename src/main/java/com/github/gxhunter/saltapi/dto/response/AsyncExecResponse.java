package com.github.gxhunter.saltapi.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2018/9/24 11:24
 *
 * 异步执行模块时，返回这种格式
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AsyncExecResponse extends BaseSaltApiResponse<AsyncExecResponse.ReturnX>{

    @Data
    @NoArgsConstructor
    public static class ReturnX{
        /**
         * SaltApi的job id
         */
        private String jid;
        /**
         * 执行该进程
         * 的被控机
         */
        private List<String> minions;
    }


}
