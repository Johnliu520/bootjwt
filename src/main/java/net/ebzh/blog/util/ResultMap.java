package net.ebzh.blog.util;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultMap {

    public static final Map<String, Object> SUCCESS_MAP = convertMap(ConstCode.SUCCESS_CODE);
    public static final Map<String, Object> FAIL_MAP = convertMap(ConstCode.FALSEA_CODE);


    public static Map<String, Object> convertMap(Integer status)
    {
        return convertMap(status, null, null);
    }

    public static Map<String, Object> convertMap(Integer status, Object data)
    {
        return convertMap(status, data, null);
    }


    public static Map<String, Object> convertMap(Integer status, String message)
    {
        return convertMap(status, null, message);
    }

    public static Map<String, Object> convertMap(Integer status, Object data, String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message==null?"":message);
        return map;
    }

    public static Map<String, Object> convertMap(Integer status, Object data, String message, String total)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        List<Object> list = new LinkedList<>(  );
        list.add( data );
        map.put("data", list);
        map.put("message", message==null?"":message);
        map.put( "total",total );
        return map;
    }


    public static Map<String, Object> getSuccessMap(Object data)
    {
        return convertMap(ConstCode.SUCCESS_CODE, data);
    }

    public static Map<String, Object> getSuccessMap(Object da,String msg)
    {
        return convertMap(ConstCode.SUCCESS_CODE, da,msg);
    }

    public static Map<String,Object> getSuccessMap(){
        return convertMap(ConstCode.SUCCESS_CODE);
    }
    public static Map<String,Object>  getSuccessMap(String message){
        return convertMap(ConstCode.SUCCESS_CODE,message);
    }
    public static Map<String, Object> getFailMap(String message)
    {
        return convertMap(ConstCode.FALSEA_CODE, message);
    }

    public static Map<String, Object> getFailMap(Object da,String message)
    {
        return convertMap(ConstCode.FALSEA_CODE,da, message);
    }

}
