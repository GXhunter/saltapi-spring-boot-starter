package com.github.gxhunter.saltapi.factory;

import com.github.gxhunter.saltapi.dto.request.SaltApiExecRequest;
import com.github.gxhunter.saltapi.enums.ClientType;
import com.github.gxhunter.saltapi.enums.ExprForm;
import com.github.gxhunter.saltapi.enums.ISaltModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Collections;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2018/10/8 16:56
 * <p>
 * 较为简单的salt请求参数生成，一般是调用内置模块
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestFactory{
    /**
     * tgt匹配规则
     */
    private ExprForm exprForm;
    /**
     * SaltStack模块名称
     */
    private ISaltModule moduleEnum;
    /**
     * 执行命令的被控机
     */
    private List<String> tgt;
    /**
     * 传给模块的参数
     */
    private String arg;

    /**
     *
     */
    private ClientType client;

    public static RequestFactory getInstance(){
        return new RequestFactory();
    }

    public RequestFactory exprForm(ExprForm exprFormEnum){
        this.exprForm = exprFormEnum;
        return this;
    }

    /**
     * 模块有默认的client方式，可以不指定client
     * @param saltModule
     * @return
     */
    public RequestFactory saltModule(ISaltModule saltModule){
        this.moduleEnum = saltModule;
        return this;
    }

    public RequestFactory tgt(List<String> tgt){
        this.tgt = tgt;
        return this;
    }

    public RequestFactory tgt(String tgt){
        this.tgt = Collections.singletonList(tgt);
        return this;
    }

    public RequestFactory arg(String arg){
        this.arg = arg;
        return this;
    }

    /**
     * 可以不指定，saltModule有默认的client方式
     * @param client
     * @return
     */
    public RequestFactory client(ClientType client){
        this.client = client;
        return this;
    }

    public SaltApiExecRequest<String> getRequest(){
        SaltApiExecRequest<String> request = new SaltApiExecRequest<>();
        request.setTgt(this.tgt);

        if(moduleEnum != null){
            if(this.client == null){
                request.setClient(this.client);
            }
            request.setFun(this.moduleEnum.getModuleName());
        }

        request.setArg(this.arg);
        request.setExprForm(this.exprForm);
        return request;
    }

}
