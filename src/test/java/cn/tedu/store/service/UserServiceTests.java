package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LeafDust
 * @create 2019-10-14 8:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService service;

    @Test
    public void regTest() {
        try {
            User user = new User();
            user.setUsername("springMVC");
            user.setPassword("1234");
            user.setGender(2);
            user.setPhone("13422519784");
            user.setEmail("saber@qq.com");
            user.setAvatar("springMVC.png");
            service.reg(user);
        } catch (ServiceException e) {
            System.err.println(e.getClass());
        }
    }

    //登录
    @Test
    public void loginTest() {
        try {
            String username = "Saber";
            String password = "12345";
            User user = service.login(username, password);
            System.err.println(user);
        } catch (Exception e) {
            System.err.println(e.getClass());
        }
    }
}

