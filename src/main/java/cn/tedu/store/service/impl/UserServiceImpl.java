package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import java.lang.String;

import cn.tedu.store.service.service_exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;


/**
 * 处理用户相关功能的业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 从参数user中获取username
        java.lang.String username = user.getUsername();
        // 根据username查询用户数据：User result = userMapper.findByUsername(username)
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (result != null) {
            // 抛出异常：throw new UsernameDuplicateException();
            throw new UsernameDuplicateException("该用户名已被使用！");
        }

        // 补全数据-加密后的密码、盐值
        // 1. 生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 2. 取出用户提交的原始密码
        String password = user.getPassword();
        // 3. 执行加密
        String md5Password = getMd5Password(password, salt);
        // 4. 将盐值和加密后的密码补全到user对象中
        user.setSalt(salt);
        user.setPassword(md5Password);

        // 补全数据-isDelete：0
        user.setIsDelete(0);
        // 补全数据-4项日志
        Date date = new Date();
        user.setCreatedUser(username);
        user.setCreatedTime(date);
        user.setModifiedUser(username);
        user.setModifiedTime(date);

        // 执行插入用户数据，获取返回值：Integer rows = userMapper.insert(user)
        Integer rows = userMapper.insert(user);
        // 判断返回的受影响行数是否不为1
        if (rows != 1) {
            // 抛出异常：throw new InsertException();
            throw new InsertException("插入用户数据异常！请联系系统管理员！");
        }
    }

    /**
     * 处理用户登录业务
     *
     * @param username 用户名
     * @param password 用户密码
     * @return user对象
     */
    public User login(String username, String password) {
        // 根据参数username查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：没有与username匹配的数据，用户名不存在，抛出UserNotFoundException，并描述错误
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断查询结果中的isDelete是否为1,
        if (result.getIsDelete() == 1) {
            //是：用户数据被标记为“已删除”，抛出UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 根据参数password和盐值得到加密后的密码(md5Password)
        String md5Password = getMd5Password(password, salt);
        // 判断查询结果中的密码与md5Password是否不一致
        // 是：密码错误，抛出PasswordNotMatchException
        if (!md5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码不匹配！");
        }
        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        // 将新User对象返回
        return user;
    }

    /**
     * 处理用户修改密码业务
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 用户原始密码
     * @param password    用户新密码
     */
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String password) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：UserNotFoundException
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }

        // 从查询结果中获取盐值
        // 将参数oldPassword结合盐值加密得到oldMd5Password
        String salt = result.getSalt();
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断查询结果中的password和oldMd5Password是否不一致
        // 是：PasswordNotMatchException
        if (!oldMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码不匹配！");
        }
        // 将参数newPassword结合盐值加密得到newMd5Password
        String newMd5Password = getMd5Password(password, salt);
        // 执行更新密码，获取操作的返回值
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        // 判断返回的受影响行数是否不为1
        // 是：UpdateException
        if (rows != 1) {
            throw new UpdateException("更新密码时遇到未知错误！请及时联系系统管理员！");
        }
    }

    /**
     * 获取用户数据
     *
     * @param uid 用户uid
     * @return
     */
    @Override
    public User getInfoByUid(Integer uid) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：UserNotFoundException
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        //创建新的user对象，并将phone，email，gender封装进去
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * 修改用户资料
     *
     * @param uid      用户uid
     * @param username 用户名
     * @param user     用户对象
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：UserNotFoundException
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        //修改用户资料
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新资料出现未知错误！请及时联系管理员！");
        }

    }

    /**
     * 修改用户头像
     *
     * @param uid        用户uid
     * @param username   用户名
     * @param avatarPath 头像储存路径
     */
    @Override
    public void changeAvatar(Integer uid, String username, String avatarPath) {
        // 根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：UserNotFoundException
            throw new UserNotFoundException("该用户不存在！");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：UserNotFoundException
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        Integer rows=userMapper.updateAvatarByUid(uid, avatarPath, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新头像时遇到未知错误！请及时联系系统管理员！");
        }
    }

    /**
     * 执行加密
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的结果
     */
    private String getMd5Password(String password, String salt) {
        // 加密标准：将盐拼在原始密码的左右两侧，循环加密3次
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex(
                    (salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
