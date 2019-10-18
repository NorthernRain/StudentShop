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
    Integer insertUser(User user);

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
     * 修改用户资料
     *
     * @param user 用户对象
     * @return 操作成功的行数
     */
    Integer updateUserInfoByUid(User user);

    /**
     * 修改用户头像
     *
     * @param uid          用户uid
     * @param avatar       用户头像图片的储存路径
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 修改成功的行数
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);


    /**
     * 通过用户名查找用户数据
     *
     * @param username 用户名
     * @return user对象
     */
    User findUserByUsername(String username);

    /**
     * 通过uid查找用户数据
     *
     * @param uid 用户uid
     * @return user对象
     */
    User findUserByUid(Integer uid);

}
