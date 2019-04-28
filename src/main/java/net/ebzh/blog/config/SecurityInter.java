package net.ebzh.blog.config;

import net.ebzh.blog.util.JwtUtil;
import net.ebzh.blog.util.RedisDao;
import net.ebzh.blog.util.ResultMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityInter implements HandlerInterceptor {

    private final RedisDao redisDao;

    public SecurityInter(RedisDao redisDao) {
        this.redisDao = redisDao;
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if ("OPTIONS".equals(request.getMethod())){
                return true;
            }else {
                String  token = request.getHeader("Authorization");
                if (null == token || token.equals("") ){
                    SecurityInter.response(response,HttpServletResponse.SC_UNAUTHORIZED, "非法请求,请登录后再继续操作");
                    return false;
                }
                String userID = redisDao.getValue(token);
                if ( null == userID || userID.equals("")){
                    SecurityInter.response(response,HttpServletResponse.SC_UNAUTHORIZED, "登录已过期，请重新登录");
                    return false;
                }
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                JwtUtil.validateToken(token);
            }
            return true;
        }catch (Exception e){
              SecurityInter.response(response,HttpServletResponse.SC_UNAUTHORIZED, "您未登录，请登录后再继续操作");
              return false;
        }
    }

    private static void response(HttpServletResponse res, int status, String message) {
        PrintWriter writer = null;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json; charset=utf-8");
            writer = res.getWriter();
            writer.write(JSON.toJSONString(ResultMap.convertMap(status, message)));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
    }



}
