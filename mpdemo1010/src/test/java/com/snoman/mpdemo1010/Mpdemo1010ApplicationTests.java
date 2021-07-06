package com.snoman.mpdemo1010;

import com.snoman.mpdemo1010.entity.User;
import com.snoman.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        user.setName("李老汉");
        user.setEmail("whhjsh.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }
    //修改User表数据
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(5L);
        user.setAge(122);

        int row = userMapper.updateById(user);
        System.out.println("update=====:"+row);
    }



}
