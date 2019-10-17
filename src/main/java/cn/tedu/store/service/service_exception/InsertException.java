package cn.tedu.store.service.service_exception;

/**
 * @author LeafDust
 * @create 2019-10-14 8:36
 * <p>
 * 数据插入异常
 */
public class InsertException extends ServiceException {
    private static final long serialVersionUID = -3593548050545501443L;

    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
