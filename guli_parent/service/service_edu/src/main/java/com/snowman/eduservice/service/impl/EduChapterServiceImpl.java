package com.snowman.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snowman.eduservice.entity.EduChapter;
import com.snowman.eduservice.entity.EduVideo;
import com.snowman.eduservice.entity.chapter.ChapterVo;
import com.snowman.eduservice.entity.chapter.VideoVo;
import com.snowman.eduservice.mapper.EduChapterMapper;
import com.snowman.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowman.eduservice.service.EduVideoService;
import com.snowman.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author snowman
 * @since 2021-07-17
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(wrapperChapter);

        //根据课程id查所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        //封装
        List<ChapterVo> finalList = new ArrayList<>();
        //建立所有章节的ChapterId到最终List的索引，便于封装小节时候找到对应章节加进去
        HashMap<String,Integer>map1=new HashMap<>();

        //一级——章节封装
        for (int i = 0; i < eduChaptersList.size(); i++) {
            EduChapter eduChapter = eduChaptersList.get(i);
            ChapterVo Vo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,Vo);
            finalList.add(Vo);
            map1.put(eduChapter.getId(),i);
        }


        //二级——小节封装
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            VideoVo Vo = new VideoVo();
            BeanUtils.copyProperties(eduVideo, Vo);

            String chapterId = eduVideo.getChapterId();
            Integer integer = map1.get(chapterId);
            finalList.get(integer).getChildren().add(Vo);
        }
        return finalList;

    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //如果章节里面有小节就不让删除，章节为空才让删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count>0){
            throw new GuliException(20001,"还有章节，不能删除");
        }else{
            boolean result = this.removeById(chapterId);
            return result;
        }
    }
}
