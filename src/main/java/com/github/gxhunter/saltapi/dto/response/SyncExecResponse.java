package com.github.gxhunter.saltapi.dto.response;
import java.util.Map;

/**
 * @author 树荫下的天空
 * @date 2018/10/11 15:19
 *
 * 执行同步模块时  返回这种格式
 */
public class SyncExecResponse<T> extends BaseSaltApiResponse<Map<String, T>>{
    /*
     * 最终形如：
     * {
     *     "return": [
     *         {
     *             "$hostname": T
     *         }
     *     ]
     * }
     */

}
