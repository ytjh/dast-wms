package com.dast.file.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanst
 * @date 2022-12-03 15:34:20
 */
@Data
@ApiModel(description = "入参")
public class FileDto implements Serializable {


    @ApiModelProperty(value = "主键id 仅有与更新传入id")
    private Long id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String fileUrl;

    @ApiModelProperty(value = "minio系列名称")
    private String minioName;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "应用id")
    private Long applidId;

    @ApiModelProperty(value = "所属租户id")
    private Long tenantId;


    @ApiModelProperty(value = "扩展")
    private String extJson;

}
