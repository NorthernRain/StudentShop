package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

    /**
     * 注册业务
     *
     * @param user 客户端传回来的用户数据
     * @return
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        System.err.println(user);
        //注册成功
        service.reg(user);
        //返回JsonResult对象
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 登录业务
     *
     * @param username 客户端传回来的用户名
     * @param password 客户端传回来的用户密码
     * @return
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        //登录成功
        User result = service.login(username, password);
        //创建session
        session.setAttribute("uid", result.getUid());
        session.setAttribute("username", result.getUsername());
        //返回JsonResult对象,包含user对象
        return new JsonResult<User>(SUCCESS, result);
    }

    /**
     * 修改密码业务
     *
     * @param oldPassword 客户端传回来的用户的旧密码
     * @param newPassword 客户端传回来的新的用户密码
     * @param session     session对象
     * @return
     */
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword
    (@RequestParam("old_password") String oldPassword,
     @RequestParam("new_password") String newPassword,
     HttpSession session) {
        //从session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        //执行修改密码
        service.changePassword(uid, username, oldPassword, newPassword);
        //返回json数据
        return new JsonResult<Void>(SUCCESS);
    }

}
