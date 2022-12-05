package com.dast.file.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanst
 * @date 2022-12-03 15:34:20
 * 表名 minio_file
 */
@Data
@TableName("minio_file")
public class FilePo implements Serializable {


    /**
     * 主键id
     */
    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * minio系列名称
     */
    private String minioName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 应用id
     */
    private Long applidId;
    /**
     * 所属租户id
     */
    private Long tenantId;
    /**
     * 是否删除 0否 1是
     */
    @TableField(select = false)
    private String delFlag;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新人id
     */
    private Long updateId;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展
     */
    private String extJson;

}
