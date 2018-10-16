/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.repository;

import com.example.springtest1.models.Post;
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
public interface PostRepository extends MongoRepository<Post, Long>{
    
    List<Post> findByPost(@Param("post") String post);
    @Query("{ 'post' : ?0 }")
    List<Post> searchByPost(@Param("post") String post);
    List<Post> findFirst10ByUserid(@Param("userid") Long userid);
}
