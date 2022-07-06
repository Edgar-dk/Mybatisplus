package com.sias.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sias.plus.entity.User;
import com.sias.plus.mapper.UserMapper;
import com.sias.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Edgar
 * @create 2022-07-06 15:48
 * @faction:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
