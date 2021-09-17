package com.zhao.dorambg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhao.dorambg.reqvo.BaseReqVO;

public interface MyBaseService<T> extends IService<T> {

    IPage<T> getPage(BaseReqVO reqVO);

    IPage<T> getPage();

    T getDetail(BaseReqVO reqvo);

}
