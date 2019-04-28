package net.ebzh.blog.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public interface UserMapper {

    Map<String,Object> findByUserName(String username);

}
