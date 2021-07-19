package com.snowman.eduservice.service.impl;

import com.snowman.eduservice.entity.EduCourse;
import com.snowman.eduservice.entity.EduCourseDescription;
import com.snowman.eduservice.entity.VO.CourseInfoVo;
import com.snowman.eduservice.mapper.EduCourseMapper;
import com.snowman.eduservice.service.EduCourseDescriptionService;
import com.snowman.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowman.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author snowman
 * @since 2021-07-17
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService courseDescriptionService;
    //添加课程信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //存课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert=baseMapper.insert(eduCourse);
        if(insert<=0){
            throw new GuliException(20001,"添加课程基本信息失败");
        }
        //获取课程id
        String cid = eduCourse.getId();
        //存课程详细信息

        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,description);
        description.setId(cid);
        courseDescriptionService.save(description);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();


        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(courseDescription.getDescription());


        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表再修改课程描述表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);

    }
}
