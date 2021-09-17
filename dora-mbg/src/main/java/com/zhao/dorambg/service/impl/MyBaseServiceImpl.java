package com.zhao.dorambg.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.dorambg.dao.MyBaseMapper;
import com.zhao.dorambg.reqvo.BaseReqVO;
import com.zhao.dorambg.service.MyBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class MyBaseServiceImpl<M extends MyBaseMapper, T> extends ServiceImpl<MyBaseMapper<T>, T> implements MyBaseService<T> {

    @Autowired
    private M myBaseMapper;

//    private M getMapper(){
//        //获得当前类带有泛型类型的父类
//        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
//        //获得运行期的泛型类型
//        Class clazz = (Class) ptClass.getActualTypeArguments()[0];
//        return (M) SpringContextHolder.getBeans((Class) ptClass.getActualTypeArguments()[0]);
//    }

    @Override
    public IPage<T> getPage() {
        return getPage(null);
    }

    @Override
    public T getDetail(BaseReqVO reqvo) {
        return (T) myBaseMapper.selectDetail(reqvo);
    }

    @Override
    public IPage<T> getPage(BaseReqVO reqVO) {
        if (reqVO == null)
            reqVO = new BaseReqVO();
        Page<T> page;
        if (reqVO.getPageable() == 1) {
            page = new Page<>(reqVO.getCurrent(), reqVO.getSize());
            page.setRecords(myBaseMapper.selectPage(reqVO));
            page.setTotal(myBaseMapper.selectTotalCount(reqVO));
//            return myBaseMapper.selectPage(page, reqVO);
        } else {
            page = new Page<>();
            page.setRecords(myBaseMapper.selectPage(reqVO));
        }
        return page;
    }

    @Override
    public boolean removeById(Serializable id) {
        if (!this.update(new UpdateWrapper<T>().set("is_del", 1).eq("id", id)))
            return super.removeById(id);
        return true;
    }

}
