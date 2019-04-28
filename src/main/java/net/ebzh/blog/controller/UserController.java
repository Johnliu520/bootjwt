package net.ebzh.blog.controller;

import net.ebzh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/authorization")
    public Map<String,Object> login(@RequestParam Map<String,Object> params){
        return userService.login(params);
    }

    @PostMapping("/authout")
    public Map<String,Object> loginOut( HttpServletRequest request){
        return userService.loginOut(request);
    }


}
