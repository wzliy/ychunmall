package com.ychun.common.api;

public class ResponseModel {
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("success", ResultCode.SUCCESS.getCode(), data);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(message, ResultCode.FAILURE.getCode(), null);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<>(message, ResultCode.FAILURE.getCode(), data);
    }

    public static <T> BaseResponse<T> error(String message, T data, ResultCode resultCode) {
        return new BaseResponse<>(message, resultCode.getCode(), data);
    }

    public static <T> BaseResponse<T> error(String message, ResultCode resultCode) {
        return new BaseResponse<>(message, resultCode.getCode(), null);
    }


}
