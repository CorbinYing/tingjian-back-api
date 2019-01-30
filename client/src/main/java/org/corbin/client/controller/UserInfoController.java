package org.corbin.client.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.ResponseCode;
import org.corbin.common.base.ResponseResult;
import org.corbin.common.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @GetMapping("/test1")
    public ResponseResult test1(){
        String s="123";
        return ResponseResult.newInstance(1,"haha");
    }

    @GetMapping("/test2")
    public ResponseResult test2(){
        String s="123";
        return ResponseResult.newInstance(1);
    }

    @GetMapping("/test3")
    public ResponseResult test3(){
        String s="123";
        return ResponseResult.newInstance(1,"ha",s);
    }

    @GetMapping("/test4")
    public ResponseResult test4(){
        String s="123";
        return ResponseResult.newInstance(1,"234",null);
    }

   @GetMapping("/test5")
    public ResponseResult test5(){
        String s="123";
       UserInfo u=new UserInfo();
        u.setName("尹延彬");
        return ResponseResult.newInstance(ResponseCode.ERR_11002,u);
    }
    @GetMapping("/test15")
    public ResponseResult teste5(){
        String s="123";
        UserInfo u=new UserInfo();
        u.setName("尹延彬");
        List<UserInfo> list=new ArrayList<UserInfo>(){{add(u);add(u);}};
        return ResponseResult.newInstance(ResponseCode.ERR_11002,list);
    }

    @GetMapping("/test6")
    public ResponseResult test6(){
        String s="123";
        Map<String,Integer>map =Maps.newHashMap();
        map.put("map",123);
        return ResponseResult.newInstance(1,"123",map);
    }

    @GetMapping("/test7")
    public ResponseResult test7(){
        String s="123";
        List<Object> list= Lists.newArrayList(123,"你好");
        return ResponseResult.newInstance(1,list);
    }

    @GetMapping("/test8")
    public ResponseResult test8(){
        String s="123";
        Long arr[]=new Long []{1L,2L};

        return ResponseResult.newInstance(1,arr);
    }

    @GetMapping("/test")
    public ResponseResult test(){
        String s="123";
        Long arr[]=new Long []{1L,2L};

        return ResponseResult.newInstance(ResponseCode.ERR_11002,arr);
    }

    @GetMapping("/test14")
    public Long [] tedfst(){
        String s="123";
        Long arr[]=new Long []{1L,2L};

        return arr;
    }
}
