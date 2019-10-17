package cn.tedu.store.service;

import cn.tedu.store.entity.User;

import java.util.Date;

/**
 * @author LeafDust
 * @create 2019-10-12 17:09
 * 处理用户相关功能的业务层接口
 */
public interface IUserService {
    /**
     * 处理用户注册业务
     *
     * @param user 新用户数据
     */
    void reg(User user);

    /**
     * 处理用户登录业务
     *
     * @param username 用户名
     * @param password 用户密码
     * @return user对象
     */
    User login(String username, String password);

    /**
     * 处理用户修改密码业务
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 用户原始密码
     * @param password    用户新密码
     */
    void changePassword(Integer uid, String username, String oldPassword, String password);

    /**
     * 根据uid获取用户信息并显示在资料页
     *
     * @param uid 用户uid
     * @return user对象
     */
    User getInfoByUid(Integer uid);

    /**
     * 修改用户资料
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param user     用户对象
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     *
     * @param uid        用户uid
     * @param username   用户名
     * @param avatarPath 头像储存路径
     */
    void changeAvatar(Integer uid, String username, String avatarPath);
}
