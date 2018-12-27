package com.github.gxhunter.saltapi.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 执行salt-api基本请求参数
 *
 * @author 树荫下的天空
 * @param <T> 传给python模块的参数
 */

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaltApiExecRequest<T>{
    /**
     * 运行方式 定义在：{@link com.github.gxhunter.saltapi.SaltConstant}
     */
    private String client;

    /**
     * 执行命令的主机名
     */
    private String tgt;
    /**
     * 方法
     */
    private String fun;

    @JsonProperty("expr_form")
    private String exprForm;

    /**
     * 方法的参数
     */
    private T arg;

    public void setTgt(List<String> tgt){
        if(!CollectionUtils.isEmpty(tgt)){
            this.tgt = tgt.stream().reduce((a,b) -> a + "," + b).get();
        }
    }

    public void setTgt(String tgt){
        this.tgt = tgt;
    }


}