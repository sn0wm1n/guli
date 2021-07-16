package com.snowman.eduservice.controller;


import com.snowman.commonutils.R;
import com.snowman.eduservice.entity.subject.OneSubject;
import com.snowman.eduservice.service.EduSubjectService;
import com.snowman.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author snowman
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来的文件，读取文件内容
    //这个Controller是通过表单请求
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //获取上传来的文件把文件读取出来
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        List<OneSubject> list=subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

