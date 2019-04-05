package org.corbin.client.sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: AcessToken
 * @Descripton:
 */

public class AcessToken {

    /**
     * @Author: Corbin
     * @Date: 2019/2/7
     * @Param: [tokenEntity]
     * @return: java.lang.String
     * @Description: 生成token
     */
    public static TokenEntity createToken(TokenEntity tokenEntity) {
        String token ;
        //把account 保存到token中，以默认token作为token的秘钥
        token = JWT.create().withAudience(tokenEntity.getUserAccount()).sign(Algorithm.HMAC256(tokenEntity.getToken()));
        tokenEntity.setToken(token);
        return tokenEntity;
    }


    public static void main(String args[]) {
        System.out.println(new Date().toString());
    }
}
