package cn.tedu.store.service.exception;


/**
 * @author LeafDust
 * @create 2019-10-14 8:44
 * <p>
 * 用户名重复异常
 */
public class UsernameDuplicateException extends ServiceException {

    private static final long serialVersionUID = -6344582798642846632L;

    public UsernameDuplicateException() {
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
