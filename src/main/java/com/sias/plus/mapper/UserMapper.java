package com.sias.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sias.plus.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author Edgar
 * @create 2022-07-06 10:08
 * @faction:
 */
@Mapper

/*1.Mapper注解，可以动态的去生成这个接口的代理类
*   可以不用去写一个类去实现这个接口了
*   直接动态生成代理类，生成的代理类交给Spring的IOC去管理
*   注入这个接口的时候，也是Authord，或者是Resource
*   使用的也是代理类
*   */
public interface UserMapper extends BaseMapper<User> {


    /*1.返回值类型是Map的查询方式*/
    Map<String,Object>  selectMapById(Long id);
}
