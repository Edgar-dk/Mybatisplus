package com.sias.plus;

import com.sias.plus.entity.User;
import com.sias.plus.service.UserService;
import com.sias.plus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Edgar
 * @create 2022-07-06 17:54
 * @faction:
 */
@SpringBootTest
public class MybatisPlusServiceTest {


    @Autowired
    private UserServiceImpl userService;


    /*1.Service的查询总条数目*/
    @Test
    public void testCount(){
        long count = userService.count();
        System.out.println(count);
    }

    /*2.批量添加
    *   这个是按照集合的方式去添加的*/
    @Test
    public void insertBath(){
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("sd"+i);
            user.setAge(23+i);
            list.add(user);
        }
        boolean batch = userService.saveBatch(list);
        System.out.println(batch);
    }
}
