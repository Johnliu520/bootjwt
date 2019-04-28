package net.ebzh.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


@Repository
@Component
public class RedisDao  {

    @Autowired
    private StringRedisTemplate template;


    public  void seKey(String key,String value){
        ValueOperations<String,String> ops = template.opsForValue();
        ops.set(key,value,2,TimeUnit.HOURS);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    public void delValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        template.delete(key);
    }
}
