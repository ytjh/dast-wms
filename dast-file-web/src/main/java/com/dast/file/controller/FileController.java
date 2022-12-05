package com.dast.file.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dast.file.dto.FileDto;
import com.dast.file.dto.FileQueryDto;
import com.dast.file.service.IFileService;
import com.dast.file.service.IMinioService;
import com.dast.file.vo.FileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.dast.annotation.AuthControl;
import top.dast.exception.DastException;
import top.dast.ro.DastResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yuanst
 * @date 2022-12-03 15:34:20
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
@AuthControl(level =  AuthControl.Level.NONE)
public class FileController {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IMinioService minioService;

    /**
     * 根据实体对象条件分页查询
     */
    @PostMapping("/queryPage")
    @ApiOperation(value = "根据实体对象条件分页查询")
    public DastResult<IPage<FileVo>> queryPage(@RequestBody FileQueryDto queryDto) {

        try {
            IPage<FileVo> iPage = fileService.queryPage(queryDto);
            return DastResult.success(iPage);
        } catch (Exception e) {
            return DastResult.fail("根据实体对象条件分页查询  异常");
        }
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    public DastResult upload(MultipartFile[] file, @RequestParam("bucketName")String bucketName){
        if (StringUtils.isEmpty(bucketName)){
            return DastResult.fail("存储名不能为空");
        }
        if (file.length<=0){
            return DastResult.fail("文件不能为空");
        }
        for (MultipartFile multipartFile:file){
            boolean flag = fileService.uploadFile(multipartFile, bucketName);
            if (!flag){
                return DastResult.fail("网络延迟，请稍后重试");
            }
        }
        return DastResult.ok();
    }

    @GetMapping("/download")
    @ApiOperation(value = "文件下载")
    public void download(@RequestParam("bucketName")String bucketName,@RequestParam("fileName")String fileName, HttpServletResponse response){
        if (StringUtils.isEmpty(bucketName)){
            return;
        }
        try {
            minioService.download(bucketName, fileName, response);
        }catch (Exception e){
            throw new DastException("文件下载失败");
        }
    }
    @GetMapping("/preView")
    @ApiOperation(value = "文件上传")
    public DastResult preView(@RequestParam("bucketName")String bucketName,@RequestParam("fileName")String fileName){
        if (StringUtils.isEmpty(bucketName)){
            return DastResult.fail("存储名不能为空");
        }
        String url = minioService.preview(fileName,bucketName);
        return DastResult.success(url);
    }


    /**
     * 添加
     *
     * @param dto
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加")
    public DastResult save(@RequestBody FileDto dto) {
        try {
            fileService.insert(dto);
        } catch (Exception e) {
            return DastResult.fail("添加  异常");
        }
        return DastResult.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public DastResult update(@RequestBody FileDto dto) {
        try {
            fileService.updateId(dto);
        } catch (Exception e) {
            return DastResult.fail("上传文件  异常");
        }
        return DastResult.ok();
    }

    /**
     * 根据主键id删除 建议修改使用逻辑删除
     */
    @PostMapping("/deleteId")
    @ApiOperation(value = "根据主键id删除")
    public DastResult deleteId(@RequestParam("id") Long id) {
        try {
            fileService.deleteById(id);
        } catch (Exception e) {
            return DastResult.fail("删除  异常");
        }
        return DastResult.ok();
    }

    /**
     * 根据主键id查询
     */
    @GetMapping("/getById")
    @ApiOperation(value = "根据主键id查询")
    public DastResult<FileVo> selectById(@RequestParam("id") Long id) {
        try {
            return DastResult.success(fileService.getById(id));
        } catch (Exception e) {
            return DastResult.fail("查询详情  异常");
        }
    }


}
