package com.snowman.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snowman.eduservice.entity.EduSubject;
import com.snowman.eduservice.entity.excel.SubjectData;
import com.snowman.eduservice.entity.subject.OneSubject;
import com.snowman.eduservice.entity.subject.TwoSubject;
import com.snowman.eduservice.listener.SubjectExcelListener;
import com.snowman.eduservice.mapper.EduSubjectMapper;
import com.snowman.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author snowman
 * @since 2021-07-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try{

            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        /*
        * 先查到两种分类，再封装成List<OneSubject>*/
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //开始封装
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //建立一级分类的最终表的id到索引的映射
        HashMap<String,Integer>map1= new HashMap<>();

        //遍历所有一级表把二级表List成员以外的信息加进去
        for (int i = 0; i <oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);
            map1.put(oneSubject.getId(),i);

        }
        //遍历所有二级表，根据map找到对应的一级表把信息添加进去
        for (int i = 0; i < twoSubjectList.size(); i++) {
            EduSubject eduSubject = twoSubjectList.get(i);
            TwoSubject twoSubject = new TwoSubject();
            BeanUtils.copyProperties(eduSubject,twoSubject);
            String pid = eduSubject.getParentId();
            Integer index = map1.get(pid);
            finalSubjectList.get(index).getChildren().add(twoSubject);
//            for (int i1 = 0; i1 < finalSubjectList.size(); i1++) {
//                OneSubject oneSubject = finalSubjectList.get(i);
//                if(oneSubject.getId()==pid){
//                    TwoSubject twoSubject = new TwoSubject();
//                    oneSubject.getChildren().add(twoSubject);
//                    break;
//                }
//            }
        }

        return finalSubjectList;
    }
}
