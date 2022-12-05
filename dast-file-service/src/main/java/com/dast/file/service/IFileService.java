package com.dast.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dast.file.dto.FileDto;
import com.dast.file.dto.FileQueryDto;
import com.dast.file.po.FilePo;
import com.dast.file.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService extends IService<FilePo> {
    /**
     * 添加
     */
    void insert(FileDto dto);

    /**
     * 根据id查询
     */
    FileVo getById(Long id);

    /**
     * 根据id修改
     */
    void updateId(FileDto dto);

    /**
     * 根据id删除
     */
    void deleteById(Long id);

    /**
     * 根据条件分页查询
     */
    IPage<FileVo> queryPage(FileQueryDto queryDto);

    /**
     * 上传文件
     * @param file
     * @param bucketName
     * @return
     */
    boolean uploadFile(MultipartFile file,String bucketName);

}
