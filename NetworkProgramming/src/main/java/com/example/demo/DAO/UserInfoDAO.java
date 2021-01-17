package com.example.demo.DAO;

import com.example.demo.Entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserInfoDAO extends CrudRepository<UserInfoEntity, Long> {
    List<UserInfoEntity> findByUsername(String username);
}
