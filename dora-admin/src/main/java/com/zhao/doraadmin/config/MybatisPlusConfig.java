package com.zhao.doraadmin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@MapperScan({"com.zhao.doraadmin.dao"})
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor generatePaginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Component
    static class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            // 对应实体需要标注@TableField(fill = FieldFill.INSERT) 或
            //                @TableField(fill = FieldFill.INSERT_UPDATE)
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            // 对应实体需要标注@TableField(fill = FieldFill.UPDATE) 或
            //                @TableField(fill = FieldFill.INSERT_UPDATE)
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

}
