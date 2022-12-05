package com.dast.file.controller;

import com.dast.file.service.IMinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dast.annotation.AuthControl;
import top.dast.ro.DastResult;

@RestController
@RequestMapping("/minio")
@Api(tags = "存储管理")
@AuthControl(level =  AuthControl.Level.NONE)
public class MinioController {

    @Autowired
    private IMinioService minioService;

    /**
     * 创建存储
     */
    @PostMapping("/makeBucket")
    @ApiOperation(value = "创建存储")
    public DastResult makeBucket(@RequestParam("bucketName") String bucketName) {
        try {
            Boolean aBoolean = minioService.makeBucket(bucketName);
            if (!aBoolean){
                return DastResult.fail("创建失败");
            }else {
                return DastResult.ok();
            }
        } catch (Exception e) {
            return DastResult.fail(e.getMessage());
        }
    }
    /**
     * 删除存储
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除存储")
    public DastResult delete(@RequestParam("bucketName") String bucketName) {
        try {
            Boolean flag = minioService.removeBucket(bucketName);
            if (!flag){
                return DastResult.fail("删除失败");
            }else {
                return DastResult.ok();
            }
        } catch (Exception e) {
            return DastResult.fail("删除  异常");
        }

    }
    /**
     * 根据存储判断存储是否存在
     */
    @PostMapping("/bucketExists")
    @ApiOperation(value = "根据存储判断存储是否存在")
    public DastResult bucketExists(@RequestParam("bucketName") String bucketName) {
        try {
            Boolean flag = minioService.bucketExists(bucketName);
            return DastResult.success(flag);
        } catch (Exception e) {
            return DastResult.fail(e.getMessage());
        }
    }
}
