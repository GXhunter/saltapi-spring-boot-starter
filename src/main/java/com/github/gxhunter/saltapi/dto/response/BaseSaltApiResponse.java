package com.github.gxhunter.saltapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2018/10/11 15:15
 * @param <T> SaltStack扩展 return的数据
 */
@Data
public class BaseSaltApiResponse<T>{
    /**
     * 最终形如：
     * {
     *     "return": [
     *         T
     *     ]
     * }
     */
    @JsonProperty("return")
    private List<T> returnX;

    /**
     * 获取return集合 内第一个对象
     * @return
     */
    @JsonIgnore()
    public T getReturnOne(){
        if(!CollectionUtils.isEmpty(getReturnX())){
            return returnX.get(0);
        }
        return null;
    }
}
