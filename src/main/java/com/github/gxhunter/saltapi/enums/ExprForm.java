package com.github.gxhunter.saltapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanggx
 * @date 2020-03-23 14:45
 **/
@AllArgsConstructor
@Getter
public enum ExprForm {
    LIST("list"),

    DEFAULT("glob");

    @JsonValue
    private String value;
}
