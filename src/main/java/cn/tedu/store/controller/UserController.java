package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LeafDust
 * @create 2019-10-14 14:43
 * <p>
 * 处理用户操作的控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService service;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        System.err.println(user);
        //注册成功
        service.reg(user);
        //返回JsonResult对象
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("login")
    public JsonResult<User> login(User user) {
        //注册成功
        User result = service.login(user.getUsername(), user.getPassword());
        //返回JsonResult对象
        return new JsonResult<User>(SUCCESS, result);
    }
}
