package com.sias.plus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Edgar
 * @create 2022-07-09 13:10
 * @faction:
 */

@Getter
public enum SexEnum {
    /*1.使用枚举的方式去增加数据*/
    MALE(1,"男"),
    FEMALE(2,"女");
    @EnumValue//将注解对应的属性的值存储到数据库中
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
