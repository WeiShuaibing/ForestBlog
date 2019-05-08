package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.UserMapper;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户管理
 *
 *
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Override
    public List<User> listUser() {
        List<User> userList = userMapper.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleMapper.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
        }
        return userList;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        User user = userMapper.getUserByNameOrEmail(str);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public int registUser(User user) {
        return userMapper.registUser(user);
    }

    @Override
    public User getUserByNameOrEmail2(String str) {
        User user = userMapper.getUserByNameOrEmail2(str);
        return user;
    }
}
