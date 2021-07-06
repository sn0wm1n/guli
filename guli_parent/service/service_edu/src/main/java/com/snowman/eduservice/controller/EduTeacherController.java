package com.snowman.eduservice.controller;


import com.snowman.eduservice.entity.EduTeacher;
import com.snowman.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author snowman
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //1.查询讲师表所有数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        List<EduTeacher> list1 = list;
        return list1;
    }
    //2.逻辑删除讲师的方法
    @DeleteMapping("{id}")
    public boolean RemoveTeacher(@PathVariable String id){
        return teacherService.removeById(id);

    }


}

