package org.corbin.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.corbin.common.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER_INFO")
public class UserInfo  extends BaseEntity implements Serializable {
    @Column(name = "USER_ACCOUNT")
    private String userAccount;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_NICKNAME")
    private String userNickName;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_DESCRIBE")
    private String userDescribe;

    @Column(name = "USER_SEX")
    private int userSex;

    @Column(name = "USER_TEL")
    private String userTel;

    @Column(name = "USER_HEAD_PORTRAIT")
    private String userHeadPortrait;

}

