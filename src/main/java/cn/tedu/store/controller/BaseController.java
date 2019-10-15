package cn.tedu.store.controller;

import cn.tedu.store.service.exception.*;
import cn.tedu.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


/**
 * @author LeafDust
 * @create 2019-10-14 15:37
 * <p>
 * 所有Controller类的基类，用于处理异常
 */

public abstract class BaseController {
    /**
     * 注册成功状态码：2000
     */
    protected static final int SUCCESS = 2000;
    /**
     * 注册失败状态码：4000
     */
    protected static final int REG_FAILED = 4000;
    /**
     * 插入数据异常状态码：5000
     */
    protected static final int INSERT_ERROR = 5000;
    /**
     * 用户不存在状态码：4001
     */
    protected static final int NOT_FOUND = 4001;
    /**
     * 用户不存在状态码：4002
     */
    protected static final int NOT_MATCH = 4002;
    /**
     * 更新密码异常：5001
     */
    protected static final int UPDATE_ERROR = 5001;

    /**
     * 从session中获取用户uid
     *
     * @param session session对象
     * @return 用户的uid
     */
    Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从session中获取用户名
     *
     * @param session session对象
     * @return 用户的用户名
     */
    String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

    //只能捕捉ServiceException及其子类异常
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        switch (e.getClass().getSimpleName()) {
            case "UsernameDuplicateException":
                //注册失败
                jsonResult.setState(REG_FAILED);
                break;
            case "InsertException":
                //插入数据异常
                jsonResult.setState(INSERT_ERROR);
                break;
            case "UserNotFoundException":
                //用户不存在异常
                jsonResult.setState(NOT_FOUND);
                break;
            case "PasswordNotMatchException":
                //密码不匹配
                jsonResult.setState(NOT_MATCH);
                break;
            case "UpdateException":
                //更新密码异常
                jsonResult.setState(UPDATE_ERROR);
                break;
        }
        return jsonResult;
    }

}
