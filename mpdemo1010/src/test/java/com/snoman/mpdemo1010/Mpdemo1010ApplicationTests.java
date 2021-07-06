package com.snoman.mpdemo1010;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snoman.mpdemo1010.entity.User;
import com.snoman.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;


    //查询所有User表所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    //添加User表数据
    @Test
    public void addUser() {
        User user = new User();
        user.setAge(22);
        user.setName("王二狗");
        user.setEmail("whhjsh.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }
    //修改User表数据
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1412215922986332161L);
        user.setAge(12223);

        int row = userMapper.updateById(user);
        System.out.println("update=====:"+row);
    }
    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1412215922986332161L);
        user.setAge(12253);

        int row = userMapper.updateById(user);
        System.out.println("update=====:"+row);
    }
    //多个id批量查询
    @Test
    public void testSelectDemo(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 3L, 5L));
        System.out.println(users);
    }

    //根据map进行的简单条件查询
    //注意：map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需
    //要填写user_id
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "王汉桑");
        map.put("age", 22);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
    //分页
    @Test
    public void testPage(){
        //1.创建Page对象(current当前页，size页面大小)
        Page<User> page = new Page<>(1,3);
        //2.调用mp分页查询方法
        //他会把查询到的数据封装到page中
        userMapper.selectPage(page,null);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//当前页数据List集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数


        System.out.println(page.hasNext());//下一页
        System.out.println(page.hasPrevious());//上一页

    }
    //物理删除
    @Test
    public void testDeleteById(){
        System.out.println(userMapper.deleteById(1L));
    }

    //批量删除
    @Test
    public void testDeleteBachIds(){
        System.out.println(userMapper.deleteBatchIds(Arrays.asList(2L, 3L)));
    }

    //法术（逻辑删除）
    @Test
    public void testLogicDelete(){
        int result=userMapper.deleteById(4L);
        System.out.println(result);

    }



}
