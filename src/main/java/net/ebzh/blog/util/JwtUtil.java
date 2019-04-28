package net.ebzh.blog.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {


        public static String generateToken(String username,String userID,String date) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userID",userID);
            map.put("userName",username);
            map.put("time",date);
            String jwt = Jwts.builder()
                    .setClaims(map)
                    .setExpiration(new Date(System.currentTimeMillis() + ConstCode.time)) //
                    .signWith(SignatureAlgorithm.HS256, ConstCode.base64Secret)
                    .compact();
            return "Bearer "+jwt; //jwt前面一般都会加Bearer
        }

        public static Map<String,Object> validateToken(String token) {
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(ConstCode.base64Secret)
                        .parseClaimsJws(token.replace("Bearer ",""))
                        .getBody();
                return body;
            }catch (Exception e){
                throw new IllegalStateException("Invalid Token. "+e.getMessage());
            }
        }


}
