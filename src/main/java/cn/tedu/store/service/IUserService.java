package cn.tedu.store.service;

import cn.tedu.store.entity.User;

/**
 * @author LeafDust
 * @create 2019-10-12 17:09
 * 处理用户相关功能的业务层接口
 */
public interface IUserService {
    /**
     * 用户注册
     *
     * @param user 新用户数据
     */
    void reg(User user);

    /**
     *
     * @param username 用户名
     * @param password 用户密码
     * @return user对象
     */
    User login(String username, String password);


}
