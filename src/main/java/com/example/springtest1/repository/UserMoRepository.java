/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.repository;

import com.example.springtest1.models.UserMo;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author bgund
 */
@Repository
public interface UserMoRepository extends MongoRepository<UserMo, Long>{
    
    List<UserMo> findByName(@Param("name") String name);
    UserMo findByUsername(@Param("username") String username);
    @Query("{ 'username' : ?0 }")
    List<UserMo> searchByUsername(@Param("post") String post);
    /*List<User> findFirst10ByUserid(@Param("userid") Long userid);*/
}

