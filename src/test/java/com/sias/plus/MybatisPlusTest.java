package com.sias.plus;

import com.sias.plus.entity.User;
import com.sias.plus.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edgar
 * @create 2022-07-06 10:16
 * @faction:
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    /*1.查询信息*/
    @Test
    public void selectAll(){

        /*01.按照全部数据*//*
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }*/

        /*02.按照id去查询数据*/
        /*User user = userMapper.selectById(1L);
        System.out.println(user);*/

        /*03.查询集合*/
        /*List<Long> longs = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(longs);
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        /*04.按照Map形式查询
        *    这个是条件查询，and并列的，两个条件都需要
        *    去查询出一个人的基本信息*/
        /*HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","dede");
        hashMap.put("age",23);
        List<User> users = userMapper.selectByMap(hashMap);
        for (User user : users) {
            System.out.println(user);
        }*/

        /*05.返回值是Map的查询方式*/
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }

    /*2.插入信息*/
    @Test
    public void insert(){
        User user = new User();
        user.setName("马龙");
        user.setAge(23);
        user.setEmail("2sd");
        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
        Long id = user.getId();
        System.out.println(id);
    }

    /*3.删除信息*/
    @Test
    public void delete(){
        int i = userMapper.deleteById(1);
        System.out.println(i);
    }


    /*4.按照Map方式去删除数据*/
    @Test
    public void deleteMap(){
        /*HashMap<Object, Object> map = new HashMap<>();
        map.put("name","马龙");
        map.put("age",23);
        int result =userMapper.deleteByMap(map);
        System.out.println(result);*/

        /*01.批量删除多个用户
        *    由于数据库长度设计的限制
        *    需要传递1L*/
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        int ids = userMapper.deleteBatchIds(longs);
        System.out.println(ids);
    }

    /*5.修改数据*/
    @Test
    public void update(){

        /*01.按照id去修改用户的信息*/
        User user = new User();
        user.setId(4L);
        user.setName("张三");
        user.setAge(34);
        int updateById = userMapper.updateById(user);
        System.out.println(updateById);
    }



}
