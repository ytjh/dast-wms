package com.dast.file;

import org.apache.dubbo.config.annotation.DubboService;
import top.dast.ro.DastResult;

/**
 * @author yuanst
 * @title: IDemoRpcServiceImpl
 * @projectName participant-center-parent
 * @description: TODO
 * @date 2022/5/2511:22
 */
@DubboService(version = "1.0.0", protocol = {"dubbo"})
public class IDemoRpcServiceImpl implements IDemoRpcService {

    @Override
    public DastResult<?> demo() {
        return DastResult.success("ssssssss");
    }
}
