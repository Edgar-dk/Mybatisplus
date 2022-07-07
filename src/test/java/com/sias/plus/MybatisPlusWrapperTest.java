package com.sias.plus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sias.plus.entity.User;
import com.sias.plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author Edgar
 * @create 2022-07-07 11:29
 * @faction:
 */
@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    /*1.查询*/
    @Test
    public void test() {

        /*01.用SQL语句去查询出复杂的表，然后在放在集合中区查找
         *    like模糊查询，between在二者之间，isnotnull这个
         *    里面的字段不可以为空*/
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*2.排序
     *   按照年龄的降序，如果年龄相同，按照id的升序*/
    @Test
    public void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age")
                .orderByAsc("uid");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*3.删除*/
    @Test
    public void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNull("email");
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);
    }

    /*4.修改
     *   将年龄大于20，并且用户名中包含a，
     *   或邮箱为null的用户修改一下
     *   名字和邮箱
     *
     *   注意：一般点的东西都是and的形式，想要去或者，需要在后面
     *   写or，这个是按照条件去修改用户的信息，这个修改的信息也是
     *   需要携带上的，所以需要在前面写user，进行携带要修改的信息*/
    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 18)
                .like("user_name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("张三");
        user.setEmail("sias@0000000000000");
        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    }


    /*5.修改的变化
     *   将用户中带a的，并且年龄大于20，或者是邮箱不为空
     *   下面and后面是先执行的*/
    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("dsdsdsdsdsdsdsds");

        int update = userMapper.update(user, wrapper);
        System.out.println("update:" + update);
    }


    /*6.查询的字段做出一定的限制*/
    @Test
    public void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    /*7.子查询的SQL语句*/
    @Test
    public void test7() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("uid", "select uid from tuser where uid <= 100");
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*8.updatewrapper的方式去修改数据*/
    @Test
    public void test8() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "小歌").set("email", "dddddddddddddddddddd");
        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }

    /*9.LambdaQuery方法
    *   查询数据*/
    @Test
    public void test09() {
        String username = "a";
        Integer ageBegin = null;
        Integer endBegin = null;

        /*01.在ge的第三个位置，对应的是数据库中的字段
        *    这个字段，要么自己写，要么是使用Lambda自身
        *    携带的方式去对应，
        *    注意：这个是底层，找到User的属性，在去按照驼峰命名法
        *    转换成数据库中字段的样子，在写到这个位置上*/
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(endBegin!=null,User::getAge,endBegin);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*10.Lambda修改方法*/
    @Test
    public void test10() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "小歌").set(User::getEmail, "dddddddddddddddddddd");
        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }
}
