package com.github.gxhunter.saltapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.TimeZone;

/**
 * 对应Response.py
 * @author 树荫下的天空
 * @date 2018/8/27 16:22
 */
@Data
public class PythonResponse{

    private Integer code;
    private String message;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endtime;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date starttime;

}
