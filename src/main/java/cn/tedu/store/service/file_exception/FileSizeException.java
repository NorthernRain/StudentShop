package cn.tedu.store.service.file_exception;

/**
 * @author LeafDust
 * @create 2019-10-17 16:49
 */
public class FileSizeException extends FileUploadException {
    private static final long serialVersionUID = 7033628808294763327L;

    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
