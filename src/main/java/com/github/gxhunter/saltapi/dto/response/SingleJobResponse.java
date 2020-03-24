package com.github.gxhunter.saltapi.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 树荫下的天空
 * @date 2018/9/29 10:30
 * @param <T>  <T> 这个job发起时，python模块接收到的参数
 * 每个被控机返回一条任务，一般是通过job_id查找时使用的
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SingleJobResponse<T> extends SyncExecResponse<SaltJobDetail<T>>{

}
