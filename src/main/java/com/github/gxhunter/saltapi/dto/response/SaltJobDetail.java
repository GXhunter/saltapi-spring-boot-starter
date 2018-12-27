package com.github.gxhunter.saltapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2018/11/3 17:58
 * salt Job详情信息
 * 用作组合，两种最终形态，{@link SingleJobResponse}和{@link JobListResponse}
 */
public class SaltJobDetail<E>{
    @JsonProperty("tgt_type")
    private String tgtType;
    private String jid;
    private String tgt;
    private Integer pid;
    private String ret;
    private String user;
    private List<E> arg;
    private String fun;
}
