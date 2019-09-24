package org.mars.user.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yaojian
 * @date 2019/8/28
 */
@Data
@Builder
public class SimpleUserVO implements Serializable {
    private static final long serialVersionUID = 7975171281550476607L;

    public SimpleUserVO() {
    }


    public SimpleUserVO(Long uid, String nickName, Integer age, String gender, String avatar) {
        this.uid = uid;
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
        this.avatar = avatar;
    }

    /**
     * 用户ID
     **/
    private Long uid;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String gender;
    /**
     * 头像地址.
     */
    private String avatar;

}
