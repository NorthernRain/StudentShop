package cn.tedu.store.controller;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.file_exception.*;
import cn.tedu.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author LeafDust
 * @create 2019-10-14 14:43
 * <p>
 * 处理用户操作的控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    /**
     * 允许上传的头像图片的最大字节数：700KB
     */
    public static final long MAX_AVATAR_SIZE = 700 * 1024;
    /**
     * 允许上传的头像的文件格式
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/gif");
        AVATAR_TYPES.add("image/bmp");
    }

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
     *
     * @param session session对象
     * @return json对象
     */
    @GetMapping("logout")
    public JsonResult<Void> logout(HttpSession session) {
        System.err.println("sessionInvalidate");
        session.invalidate();
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 修改用户头像
     *
     * @param avatar  头像文件
     * @param session session对象
     * @return 包含图片路径的json对象
     */
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("avatar") MultipartFile avatar, HttpSession session) {
        //检查上传的文件是否为空
        if (avatar.isEmpty()) {
            throw new FileEmptyException("未上传任何文件！");
        }
        //检查文件类型是否符合要求
        if (!AVATAR_TYPES.contains(avatar.getContentType())) {
            throw new FileTypeException("仅支持" + AVATAR_TYPES + "格式的图片！");
        }
        //检查文件大小是否符合要求
        if (avatar.getSize() > MAX_AVATAR_SIZE) {
            throw new FileSizeException("上传文件大小超过700KB！");
        }

        //获取original文件名
        String originalFilename = avatar.getOriginalFilename();

        //创建文件夹
        String parentPath = session.getServletContext().getRealPath("upload");
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        System.err.println(parent.getAbsolutePath());
        //截取文件后缀suffix
        int lastDotPos = originalFilename.lastIndexOf(".");
        String suffix = "";
        if (lastDotPos > 0) {
            suffix = originalFilename.substring(lastDotPos);
        }
        //创建新文件名
        String filename = UUID.randomUUID().toString() + suffix;

        //保存客户端上传的文件
        File dest = new File(parent, filename);

        // 保存客户端上传的文件
        try {
            avatar.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件转换出错！请尝试重新上传！");
        } catch (IOException e) {
            throw new FileIOException("文件读写出错！请尝试重新上传！");
        }

        //将文件的路径储存到数据库中
        String avatarPath = "/upload" + filename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.changeAvatar(uid, username, avatarPath);

        // 响应：OK, 头像路径
        return new JsonResult<String>(SUCCESS, avatarPath);

    }
}
