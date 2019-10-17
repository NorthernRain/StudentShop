package cn.tedu.store.service.file_exception;

/**
 * @author LeafDust
 * @create 2019-10-17 16:45
 */
public class FileUploadException extends RuntimeException {
    private static final long serialVersionUID = -1169841527950411266L;

    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
