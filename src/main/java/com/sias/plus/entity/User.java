package com.sias.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tuser")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
