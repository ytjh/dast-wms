package com.dast.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dast.file.po.FilePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author yuanst
 * @email ${email}
 * @date 2022-12-03 15:34:20
 */
@Mapper
public interface FileMapper extends BaseMapper<FilePo> {

    /**
     * 分页查询
     *
     * @param page
     * @param po
     * @return FilePo
     */
    IPage<FilePo> queryPage(IPage page, @Param("query") FilePo po);

    /**
     * 逻辑删除
     *
     * @param map
     */
    void delFlag(Map map);

}
