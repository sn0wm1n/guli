package com.snoman.mpdemo1010.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId(type= IdType.ID_WORKER)//雪花算法
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)//第一次添加
    private Date createTime;

    @TableField(fill=FieldFill.INSERT_UPDATE)//加insert让他第一次添加也有值
    private Date updateTime;

    @Version
    private Integer version;//版本号

    @TableLogic
    private Integer deleted;



}
