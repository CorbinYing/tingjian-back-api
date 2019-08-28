package org.corbin.client.sercurity;

import lombok.Data;
import org.corbin.common.util.MD5Util;

import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: TokenEntity
 * @Descripton:
 */
@Data
public class TokenEntity {
    private String token;
    private  String userAccount;
    private Long expiresIn; //有效期  秒
    private Date createTime;//创建时间


    public TokenEntity(String userAccount){
        token= MD5Util.convertMD5(userAccount);
        this.userAccount=userAccount;
        //expiresIn=1000
    }
}
