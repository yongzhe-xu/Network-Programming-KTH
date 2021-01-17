package com.example.demo.DAO;


import com.example.demo.Entity.FileURLEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileUrlDAO extends CrudRepository<FileURLEntity, Long> {
    List<FileURLEntity> findByGroupNum(String groupNum);
}
