package com.dast.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dast.file.constans.FileCommonConstans;
import com.dast.file.constans.FileCommonMap;
import com.dast.file.dto.FileDto;
import com.dast.file.dto.FileQueryDto;
import com.dast.file.mapper.FileMapper;
import com.dast.file.po.FilePo;
import com.dast.file.service.IFileService;
import com.dast.file.service.IMinioService;
import com.dast.file.vo.FileVo;
import com.google.common.collect.Lists;
import io.minio.StatObjectResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.dast.enums.DelFlagEnum;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FilePo> implements IFileService {

    @Autowired
    private IMinioService minioService;

    @Resource
    private FileMapper mapper;


    @Override
    public void insert(FileDto dto) {
        FilePo po = new FilePo();
        BeanUtils.copyProperties(dto, po);
        this.save(po);
    }

    @Override
    public FileVo getById(Long id) {
        FilePo po = mapper.selectById(id);
        if (po == null) {
            return null;
        }
        FileVo vo = new FileVo();
        BeanUtils.copyProperties(po, vo);
        return vo;

    }

    @Override
    public void updateId(FileDto dto) {
        FilePo po = new FilePo();
        BeanUtils.copyProperties(dto, po);
        mapper.updateById(po);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        FilePo po = mapper.selectById(id);
        this.removeById(id);
        minioService.deleteFile(po.getMinioName(),po.getFileName());
    }

    @Override
    public IPage<FileVo> queryPage(FileQueryDto queryDto) {

        Page page = new Page(queryDto.getPageNum(), queryDto.getPageSize());
        FilePo po = new FilePo();
        BeanUtils.copyProperties(queryDto, po);
        IPage<FilePo> poIPage = mapper.queryPage(page, po);
        IPage<FileVo> voIPage = new Page<>();
        if (poIPage != null && poIPage.getRecords().size() > 0) {
            List<FileVo> userVoList = Lists.newArrayList();
            poIPage.getRecords().forEach(ipo -> {
                FileVo vo = new FileVo();
                BeanUtils.copyProperties(ipo, vo);
                userVoList.add(vo);
            });
            voIPage.setRecords(userVoList);
            voIPage.setTotal(poIPage.getTotal());
            voIPage.setCurrent(poIPage.getCurrent());
            voIPage.setSize(poIPage.getSize());
            voIPage.setPages(poIPage.getPages());
        }
        return voIPage;
    }

    @Override
    public boolean uploadFile(MultipartFile file, String bucketName) {
        Boolean flag = minioService.upload(file, bucketName);
        if (!flag){
            return false;
        }
        StatObjectResponse object = minioService.getFileObject(bucketName, file.getOriginalFilename());
        if (object==null){
            return false;
        }
        FilePo po = new FilePo();
        po.setFileName(file.getOriginalFilename());
        po.setFileUrl(bucketName+ FileCommonConstans.FILE_SYMBOL.FILE_SLASH+po.getFileName());
        String  fileSuffix = file.getOriginalFilename().substring(po.getFileName().lastIndexOf(".")+1,po.getFileName().length());
        po.setFileType(FileCommonMap.getFileType(fileSuffix));
        po.setMinioName(bucketName);
        po.setFileSize(object.size());
        this.save(po);
        return true;
    }


}
