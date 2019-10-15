package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author LeafDust
 * @create 2019-10-12 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("Saber");
        user.setPassword("1234");
        user.setGender(2);
        user.setPhone("13422519784");
        user.setEmail("saber@qq.com");
        Integer rows = userMapper.insert(user);
        System.err.println(rows + ":" + user);
    }

    @Test
    public void findByUsername() {
        String username = "Saber";
        User user = userMapper.findByUsername(username);
        System.err.println(user);
    }
}
