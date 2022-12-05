package com.dast.file.service;

import io.minio.StatObjectResponse;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IMinioService {
    /**
     * 查看存储bucket是否存在
     *
     * @param bucketName 存储bucket
     * @return boolean
     */
    Boolean bucketExists(String bucketName);

    /**
     * 创建存储bucket
     *
     * @param bucketName 存储bucket名称
     * @return Boolean
     */
    Boolean makeBucket(String bucketName);

    /**
     * 删除存储bucket
     *
     * @param bucketName 存储bucket名称
     * @return Boolean
     */
    Boolean removeBucket(String bucketName);

    /**
     * 文件上传
     *
     * @param file       文件
     * @param bucketName 存储bucket
     * @return Boolean
     */
    Boolean upload(MultipartFile file, String bucketName);

    /**
     * 文件上传 若不想将已有名字的文件覆盖，则重新生成文件名
     *
     * @param file       文件
     * @param bucketName 存储bucket
     * @param fileName   文件名称
     * @return Boolean
     */
    Boolean uploadFileName(MultipartFile file, String bucketName, String fileName);

    /**
     * 文件下载
     *
     * @param bucketName 存储bucket名称
     * @param fileName   文件名称
     * @param res        response
     * @return Boolean
     */
    void download(String bucketName, String fileName, HttpServletResponse res);

    /**
     * 查看文件对象
     *
     * @param bucketName 存储bucket名称
     * @return 存储bucket内文件对象信息
     */
    List<Item> listObjects(String bucketName);

    /**
     * 根据存储名 文件名删除文件
     *
     * @param bucketName
     * @param objectName
     */
    void deleteFile(String bucketName, String objectName);

    /**
     * 预览
     * @param fileName
     * @return
     */
    public String preview(String fileName,String bucketName);

    /**
     * 获取文件信息
     * @param bucketName
     * @param fileName
     * @return
     */
    StatObjectResponse getFileObject(String bucketName, String fileName);
}
