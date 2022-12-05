package com.dast.file.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanst
 * @date 2022-12-03 15:34:20
 * 表名 minio_file
 */
@Data
@ApiModel(description = "出参")
public class FileVo implements Serializable {


    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件路径")
    private String fileUrl;
    @ApiModelProperty(value = "minio系列名称")
    private String minioName;
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    @ApiModelProperty(value = "应用id")
    private Long applidId;
    @ApiModelProperty(value = "所属租户id")
    private Long tenantId;
    @ApiModelProperty(value = "创建人id")
    private Long createId;
    @ApiModelProperty(value = "创建人名称")
    private String createName;
    @ApiModelProperty(value = "更新人id")
    private Long updateId;
    @ApiModelProperty(value = "更新人名称")
    private String updateName;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "扩展")
    private String extJson;

}
