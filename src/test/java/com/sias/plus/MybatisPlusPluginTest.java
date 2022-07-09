package com.sias.plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sias.plus.entity.Product;
import com.sias.plus.entity.User;
import com.sias.plus.mapper.ProductMapper;
import com.sias.plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Edgar
 * @create 2022-07-07 21:26
 * @faction:
 */
@SpringBootTest
public class MybatisPlusPluginTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    /*1.分呀查询
    *   current表示第几页
    *   size每一页有多少行，后面的null是要去查询的条件是什么
    *   null是空，代表查询全部的*/
    @Test
    public void testPage(){
        Page<User> userPage = new Page<>(2,3);

        /*01.hasNext是否有上一页
        *    hasPrevious是否有下一页*/
        Page<User> page = userMapper.selectPage(userPage, null);
        System.out.println(page.hasNext()+"dsdsdsdas");
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.hasPrevious());
    }

    /*2.乐观锁*/
    @Test
    public void Lock(){
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("+++++++++++++++"+p1);
        System.out.println("小李取出的价格：" + p1.getPrice());
        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());
        //3、小李将价格加了50元，存入了数据库
         p1.setPrice(p1.getPrice() + 50);
         int result1 = productMapper.updateById(p1);
         System.out.println("小李修改结果：" + result1);

         /*4.小王将商品减了30元，存入了数据库
             一开始的时候，第一次获取的version是一个数
             但是在这个操作之前，小李操作的数据库，把数据更改了
             版本号不一样，所以操作失败，这次在做一下判断
             这个判断就是从数据库中从新获取数据
          */
         p2.setPrice(p2.getPrice() - 30);
         int result2 = productMapper.updateById(p2);
         if (result2==0){
             Product product = productMapper.selectById(1);
             product.setPrice(product.getPrice()-30);
             int i = productMapper.updateById(product);
             System.out.println("优化操作："+i);
         }
         System.out.println("小王修改结果：" + result2);
        // 最后的结果
         Product p3 = productMapper.selectById(1L);
        // 价格覆盖，最后的结果：70
         System.out.println("最后的结果：" + p3.getPrice());
    }
}
