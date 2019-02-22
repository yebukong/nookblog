package pers.mine.nookblog;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

public enum ErrorCode implements IErrorCode {
    TEST(1000L, "测试错误编码");

    private Long code;
    private String msg;

    ErrorCode(final Long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
