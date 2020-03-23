package com.github.gxhunter.saltapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanggx
 * @date 2020-03-23 14:40
 **/
@AllArgsConstructor
@Getter
public enum ClientType {
    CLIENT_LOCAL("local"),
    CLIENT_LOCAL_ASYNC("local_async"),
    CLIENT_RUNNER("runner"),
    ;
    @JsonValue
    private String value;
}
