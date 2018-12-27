package com.github.gxhunter.saltapi.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * python执行结果
 * @author 树荫下的天空
 * @date 2018/6/21 11:19
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PythonBackupResponse extends PythonResponse{
    /**
     * 备份路径
     */
    @JsonProperty("backup_file_path")
    private String backupFilePath;
    /**
     * 备份文件名
     */
    @JsonProperty("backup_file_name")
    private String backupFileName;
}
