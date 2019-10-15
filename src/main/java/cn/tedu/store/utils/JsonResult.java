package cn.tedu.store.utils;

/**
 * @param <T> 服务器端向客户端响应的数据
 * @author LeafDust
 * @create 2019-10-14 14:33
 * <p>
 * 封装服务器端向客户端响应的结果
 */

public class JsonResult<T> {
    /**
     * json对象返回的状态代码
     */
    private Integer state;
    /**
     * json对象的状态信息
     */
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
