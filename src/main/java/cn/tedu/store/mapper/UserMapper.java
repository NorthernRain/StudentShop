package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author LeafDust
 * @create 2019-10-12 15:36
 */
public interface UserMapper {
    Integer insert(User user);

    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    User findByUsername(String username);

    User findByUid(Integer uid);

}
