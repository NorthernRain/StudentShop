package cn.tedu.store.service.service_exception;

/**
 * @author LeafDust
 * @create 2019-10-14 8:48
 * <p>
 * 业务异常
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 3479587231040893413L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
