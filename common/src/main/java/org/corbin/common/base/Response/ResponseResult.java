package org.corbin.common.base.Response;

import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseResult<T>  implements Serializable {

    private Integer code;
    private String  msg;
    private T result;

    public ResponseResult(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static ResponseResult newInstance(Integer code) {
        return newInstance(code, null);
    }

    public static ResponseResult newInstance(Integer code, String msg) {
        return newInstance(code, msg, null);
    }

    public static ResponseResult newInstance(Integer code, String msg, Object result) {
        String defaultMsg = ResponseDefaultMsg.getDefaultMsg(code);
        defaultMsg = (msg == null ? defaultMsg : defaultMsg + "," + msg);
        result = (result == null ? Maps.newHashMap() : result);
        return new ResponseResult(code, defaultMsg, result);
    }

    public static ResponseResult newInstance(Integer code, Object result) {
        return newInstance(code, null, result);
    }

    /**
     * 判断ResponseResult 对象是否为空
     */
    public static boolean isResponseResultNull(ResponseResult responseResult){
        if (responseResult==null){
            return true;
        }
        return false;
    }
    /**
     * 判断ResponseResult属性值result是否为空
     */
    public static boolean isResponseResultNotNull(ResponseResult responseResult){
        return isResponseResultNull(responseResult);
    }



    public static void main(String []args){
        ResponseResult r= ResponseResult.newInstance(ResponseCode.ERR_11002);
        System.out.println(r);
        System.out.println();
    }

}
