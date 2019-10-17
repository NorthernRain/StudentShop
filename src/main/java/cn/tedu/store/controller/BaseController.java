package cn.tedu.store.controller;

import cn.tedu.store.service.file_exception.*;
import cn.tedu.store.service.service_exception.*;
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
    protected static final Integer SUCCESS = 2000;
    /**
     * 注册失败状态码：4000
     */
    protected static final Integer REG_FAILED = 4000;

    /**
     * 用户不存在状态码：4001
     */
    protected static final Integer NOT_FOUND = 4001;
    /**
     * 密码不匹配状态码：4002
     */
    protected static final Integer NOT_MATCH = 4002;

    /**
     * 文件为空状态码：4002
     */
    protected static final Integer FILE_EMPTY_ERROR = 4003;

    /**
     * 超出文件大小状态码：4002
     */
    protected static final Integer FILE_SIZE_ERROR = 4004;

    /**
     * 文件类型不匹配状态码：4002
     */
    protected static final Integer FILE_TYPE_ERROR = 4005;

    /**
     * 文件转换异常状态码：4002
     */
    protected static final Integer FILE_STATE_ERROR = 4006;

    /**
     * 文件读写异常状态码：4002
     */
    protected static final Integer FILE_IO_ERROR = 4007;

    /**
     * 插入数据异常状态码：5000
     */
    protected static final Integer INSERT_ERROR = 5000;

    /**
     * 更新密码异常：5001
     */
    protected static final Integer UPDATE_ERROR = 5001;

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
    String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }

    //只能捕捉ServiceException,FileUploadException及其子类异常
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
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
            case "FileEmptyException":
                //上传文件为空
                jsonResult.setState(FILE_EMPTY_ERROR);
                break;
            case "FileTypeException":
                //上传文件为空
                jsonResult.setState(FILE_TYPE_ERROR);
                break;
            case "FileSizeException":
                //上传文件为空
                jsonResult.setState(FILE_SIZE_ERROR);
                break;
            case "FileStateException":
                //上传文件为空
                jsonResult.setState(FILE_STATE_ERROR);
                break;
            case "FileIOException":
                //上传文件为空
                jsonResult.setState(FILE_IO_ERROR);
                break;
        }
        return jsonResult;
    }

}
