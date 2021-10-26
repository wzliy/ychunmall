package com.ychun.account.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String username;
    private String nickname;
    private String realname;
    private String password;
    private String face;
    private String mobile;
    private int sex;
    private Date birthday;
    private String email;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    private Date updateTime;

}
