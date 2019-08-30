package org.mars.user.domain;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String nickName;

    private String name;

    private Integer age;

    private Byte gender;

    private String pwd;

    private String avatar;

    private String phone;

    private String email;


}