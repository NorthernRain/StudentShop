package cn.tedu.store.service.file_exception;

/**
 * @author LeafDust
 * @create 2019-10-17 16:46
 */
public class FileStateException extends FileUploadException {

    private static final long serialVersionUID = -3005573115172676930L;

    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
