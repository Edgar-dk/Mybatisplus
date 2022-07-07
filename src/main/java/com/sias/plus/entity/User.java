package com.sias.plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Edgar
 * @create 2022-07-06 9:54
 * @faction:
 */
@Data
@NoArgsConstructor//无参数的构造
@AllArgsConstructor//有参数的构造
@TableName("tuser")//实体类中的名字用这个注解对应到数据库中的名字
public class User {


    /*1.用这个注解，作为主键，MybatisPlus默认是id
    *   使用之后，默认是uid
    *   value用于指定主键的字段
    *
    *   id到数据库中是自增的*/
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;


    /*2.指定属性对应到数据库中的字段*/
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;


    /*3.逻辑删除字段
    *   使用之后，会吧里面的标识符变成1，1表示
    *   是逻辑删除，其实是修改的方式，把0变成了1*/
    @TableLogic
    private Integer isDeleted;
}
