package org.mars.common.result;

import lombok.Data;

/**
 * @author yaojian
 * @version 2019/8/28
 */
@Data
public class Result {


    private int code;
    private Object data;
    private String msg;


    private Result(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public static Result success(Object data) {
        return new Result(ResultCode.CODE_SUCCESS, data, ResultCode.MSG_SUCCESS);
    }


}
