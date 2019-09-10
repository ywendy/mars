package org.mars.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    public User() {
    }

    public User(Long id, Long uid, String loginName, String nickName, String name, Integer age, Byte gender, String pwd, String salt, String avatar, String phone, String email) {
        this.id = id;
        this.uid = uid;
        this.loginName = loginName;
        this.nickName = nickName;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pwd = pwd;
        this.salt = salt;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
    }

    private Long id;

    private Long uid;

    private String loginName;

    private String nickName;

    private String name;

    private Integer age;

    private Byte gender;

    private String pwd;

    private String salt;

    private String avatar;

    private String phone;

    private String email;


}