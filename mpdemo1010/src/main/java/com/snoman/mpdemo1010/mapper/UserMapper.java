package com.snoman.mpdemo1010.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snoman.mpdemo1010.entity.User;
import org.springframework.stereotype.Repository;

@Repository//加了之后能不报红，但是可加可不加
public interface UserMapper extends BaseMapper<User> {
}
