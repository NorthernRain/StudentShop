package cn.tedu.store.service.file_exception;

/**
 * @author LeafDust
 * @create 2019-10-17 16:46
 */
public class FileTypeException extends FileUploadException {

    private static final long serialVersionUID = -6061333767560746493L;

    public FileTypeException() {
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
