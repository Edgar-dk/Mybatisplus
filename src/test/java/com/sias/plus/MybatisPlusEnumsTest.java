package com.sias.plus;

import com.sias.plus.entity.User;
import com.sias.plus.enums.SexEnum;
import com.sias.plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Edgar
 * @create 2022-07-09 13:20
 * @faction:
 */

@SpringBootTest
public class MybatisPlusEnumsTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setId(6L);
        user.setName("admin");
        user.setAge(1222222);
        /*01.在去设置数据的时候，把数据通过枚举方式
        *    设置好，然后在去放在对象中*/
        user.setSex(SexEnum.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
