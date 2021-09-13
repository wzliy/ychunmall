package com.ychun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举值
 */
@Getter
@AllArgsConstructor
public enum Sex {
    Woman(0, "男"),
    Man(1, "女"),
    Secret(2, "保密");
    private int type;
    private String value;
}
