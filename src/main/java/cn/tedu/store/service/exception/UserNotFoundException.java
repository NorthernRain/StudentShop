package cn.tedu.store.service.exception;

/**
 * @author LeafDust
 * @create 2019-10-15 9:47
 * <p>
 * 用户名为null异常
 */
public class UserNotFoundException extends ServiceException {
    private static final long serialVersionUID = -7935284869031461969L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
