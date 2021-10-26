package com.ychun.account.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
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
    private String email;
    private int sex;
    private Date birthday;
    private String createUser;
    private Date CreateTime;
}
