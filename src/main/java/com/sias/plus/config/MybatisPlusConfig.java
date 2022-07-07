package com.sias.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Edgar
 * @create 2022-07-07 21:23
 * @faction:
 */
@Configuration
public class MybatisPlusConfig {

    /*1.Mybatis的分页插件*/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*01.添加分页插件*/
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        /*02.添加乐观锁插件*/
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
