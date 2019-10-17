package cn.tedu.store.service.file_exception;

/**
 * @author LeafDust
 * @create 2019-10-17 16:47
 */
public class FileIOException extends FileUploadException {
    private static final long serialVersionUID = 5731223201288618474L;

    public FileIOException() {
    }

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }

    public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
