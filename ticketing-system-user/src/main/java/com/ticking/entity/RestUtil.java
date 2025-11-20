package com.ticking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestUtil {
    private String msg;
    private Object data;
    private Integer code;
    public static RestUtil success(){
        return new RestUtil("success",null,200);
    }
    public static RestUtil success(Object data){
        return new RestUtil("success",data,200);
    }
    public static RestUtil error(String msg){
        return new RestUtil(msg,null,500);
    }
    public static RestUtil error(String msg,Object data){
        return new RestUtil(msg,data,500);
    }
}
