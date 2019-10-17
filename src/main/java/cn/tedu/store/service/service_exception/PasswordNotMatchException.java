package cn.tedu.store.service.service_exception;

/**
 * @author LeafDust
 * @create 2019-10-15 9:48
 * <p>
 * 密码错误异常
 */
public class PasswordNotMatchException extends ServiceException {
    private static final long serialVersionUID = 3421720783905358548L;

    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
