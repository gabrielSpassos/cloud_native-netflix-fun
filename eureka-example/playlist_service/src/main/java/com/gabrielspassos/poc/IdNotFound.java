package com.gabrielspassos.poc;

public class IdNotFound extends Exception{

    private String msg;

    public IdNotFound(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
