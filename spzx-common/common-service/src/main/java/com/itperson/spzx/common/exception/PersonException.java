package com.itperson.spzx.common.exception;

import com.itperson.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class PersonException extends RuntimeException{

    public Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public PersonException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();

    }

}
