package com.cll.yygh.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@ApiModel("自定义全局异常类")
@Data
public class YyghException extends RuntimeException{
    private Integer code;

    public YyghException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
