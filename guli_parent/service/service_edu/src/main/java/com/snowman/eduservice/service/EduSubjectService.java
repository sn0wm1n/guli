package com.snowman.eduservice.service;

import com.snowman.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowman.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author snowman
 * @since 2021-07-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
