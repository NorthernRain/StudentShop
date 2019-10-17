package cn.tedu.store.service.service_exception;

/**
 * @author LeafDust
 * @create 2019-10-15 15:39
 * <p>
 * 更新数据异常
 */
public class UpdateException extends ServiceException {
    private static final long serialVersionUID = -4323982443401136057L;

    public UpdateException() {
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
