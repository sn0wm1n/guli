package com.snowman.eduservice.service;

import com.snowman.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowman.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author snowman
 * @since 2021-07-17
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
