package com.github.gxhunter.saltapi.dto.response;

import lombok.Data;
import java.util.List;

/**
 * @author 树荫下的天空
 * @param <T> 这个job发起时，python模块接收到的参数
 * 每个被控机返回多条任务
 */
@Data
public class JobListResponse<T> extends SyncExecResponse<List<SaltJobDetail<T>>>{
    /**
     * 最终形如：
     * {
     *     "return": [
     *         {
     *             "$hostname": [
     *                  Response<T>
     *             ]
     *         }
     *     ]
     * }
     */
}
