package com.itbin.utils;

import java.io.Serializable;

public class SystemException extends  Exception implements Serializable  {
    private  String message;

    public SystemException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
