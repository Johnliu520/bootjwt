package net.ebzh.blog.controller;

import net.ebzh.blog.util.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/getBlogUrl")
    public Map<String,Object> getBlogUrl(){
        Map<String,Object> data = new HashMap<>();
        data.put("url","http://blog.ebzh.net");
        data.put("blogName","刹那芳华");
        return ResultMap.getSuccessMap(data);
    }

}
