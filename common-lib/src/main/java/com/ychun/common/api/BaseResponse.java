package com.ychun.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseResponse<T> {
    private String message;
    @Builder.Default
    private int code = ResultCode.SUCCESS.getCode();
    private T data;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS.getCode();
    }
}
