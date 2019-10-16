package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author LeafDust
 * @create 2019-10-12 15:36
 */
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 修改密码
     *
     * @param uid          用户id
     * @param password     用户新密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 操作成功的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据uid修改用户的资料
     * @param uid
     * @param gender
     * @param phone
     * @param email
     * @return
     */
        Integer updateInfoByUid(@Param("uid") Integer uid,
                                @Param("gender") Integer gender,
                                @Param("phone") String phone,
                                @Param("email") String email);

    /**
     * 通过用户名查找用户数据
     *
     * @param username 用户名
     * @return user对象
     */
    User findByUsername(String username);

    /**
     * 通过uid查找用户数据
     *
     * @param uid 用户uid
     * @return user对象
     */
    User findByUid(Integer uid);

}
