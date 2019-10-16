package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("reg")
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
    @PostMapping("login")
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
    @PostMapping("change_password")
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

    /**
     * 获取用户数据展示在资料页
     *
     * @param session session对象
     * @return json对象
     */
    @GetMapping("get_info")
    public JsonResult<User> getInfoByUid(HttpSession session) {
        User user = service.getInfoByUid(getUidFromSession(session));
        return new JsonResult<User>(SUCCESS, user);
    }

    /**
     * 修改用户资料
     *
     * @param user    获取客户端传回来的用户数据
     * @param session session对象
     * @return json对象
     */
    @PostMapping("change_info")
    public JsonResult<Void> changeInfoByUid(User user, HttpSession session) {
        service.changeInfo(getUidFromSession(session), getUsernameFromSession(session), user);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 登出
     * @param session session对象
     * @return json对象
     */
    @GetMapping("logout")
    public JsonResult<Void> logout(HttpSession session) {
        System.err.println("sessionInvalidate");
        session.invalidate();
        return new JsonResult<>(SUCCESS);
    }
}
