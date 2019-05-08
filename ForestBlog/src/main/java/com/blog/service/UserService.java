package com.blog.service;

import com.blog.entity.User;

import java.util.List;

/**
 *
 *
 */

public interface UserService {
    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listUser();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户
     */
    User insertUser(User user);

    /**
     * 添加用户
     *
     * @param user 注册普通用户，非管理眼
     * @return 受影响行数
     */
    int registUser(User user);

    /**
     * 根据用户名和邮箱查询用户管理員
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名和邮箱查询普通用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail2(String str);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     *
     * @param email Email
     * @return 用户
     */
    User getUserByEmail(String email);
}
