package com.github.gxhunter.saltapi.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.gxhunter.saltapi.enums.ClientType;
import com.github.gxhunter.saltapi.enums.ExprForm;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 执行salt-api基本请求参数
 *
 * @param <T> 传给python模块的参数
 * @author 树荫下的天空
 */

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaltApiExecRequest<T> {
    private ClientType client;

    /**
     * 执行命令的主机名
     */
    private String tgt;
    /**
     * 方法
     */
    private String fun;

    @JsonProperty("expr_form")
    private ExprForm exprForm;

    /**
     * 方法的参数
     */
    private T arg;

    public void setTgt(List<String> tgt) {
        if (!CollectionUtils.isEmpty(tgt)) {
            this.tgt = StringUtils.join(tgt, ",");
        }
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }

    @Builder
    public SaltApiExecRequest(String tgt, String fun, ExprForm exprForm, T arg) {
        this.tgt = tgt;
        this.fun = fun;
        this.exprForm = exprForm;
        this.arg = arg;
    }
}
