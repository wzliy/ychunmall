package com.ychun.account.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户列表数据类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserList {
    /**
     * 用户列表数据
     */
    private List<UserDto> userList;

    /**
     * 当前页码
     */
    private int pageNumber;

    /**
     * 总页数
     */
    private int totalPage;

    private long total;


}
