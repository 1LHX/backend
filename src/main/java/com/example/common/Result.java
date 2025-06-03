package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "成功", data);
    }

    public static Result success() {
        return new Result(200, "成功", null);
    }

    public static Result error(String msg) {
        return new Result(500, msg, null);
    }
}