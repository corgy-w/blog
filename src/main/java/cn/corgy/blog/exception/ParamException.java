package cn.corgy.blog.exception;

import lombok.Getter;

@Getter
public class ParamException extends RuntimeException {
    private Integer code = 500;
    private String msg = "参数异常";

    public ParamException() {
        super("参数异常");
    }

    public ParamException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamException(Integer code) {
        super("参数异常");
        this.code = code;
    }

    public ParamException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
