package net.ebzh.blog.service;

import net.ebzh.blog.dao.UserMapper;
import net.ebzh.blog.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisDao redisDao;

    public Map<String,Object> login(Map<String,Object> params){

        Map<String,Object> userMap = userMapper.findByUserName(params.get("username").toString());
        if (null == userMap){
            return ResultMap.getFailMap("您的账户不存在！请检查后再登陆");
        }

        if ("1".equals(userMap.get("state").toString())){
            return ResultMap.getFailMap("您的账户已被锁定！请联系管理员");
        }

        String userPassword = MD5.stringMD5(params.get("password").toString());
        String userMapPassword = userMap.get("password").toString();

        if (userPassword.equals(userMapPassword)){
            String Token = JwtUtil.generateToken(userMap.get("username").toString(), userMap.get("id").toString(), DateUtils.getNowTime());
            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("token",Token);
            redisDao.seKey(Token,userMap.get("id").toString());
            return ResultMap.getSuccessMap(tokenMap,"登录成功");
        }else{
            return ResultMap.getFailMap("密码错误！请检查后再登陆");
        }
    }

    public Map<String,Object> loginOut(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        redisDao.delValue(token);
        return ResultMap.getSuccessMap("退出成功");

    }

}
