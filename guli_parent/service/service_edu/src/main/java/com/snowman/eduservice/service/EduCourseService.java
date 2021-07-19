package com.snowman.eduservice.service;

import com.snowman.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowman.eduservice.entity.VO.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author snowman
 * @since 2021-07-17
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
