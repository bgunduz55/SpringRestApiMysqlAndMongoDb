/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.repository;

/**
 *
 * @author bgund
 */
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
 
import com.example.springtest1.models.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
     @Query("select u from users u where LOWER(u.name) LIKE LOWER(CONCAT('%',:name, '%'))")
     @Cacheable("users")
     public List<User> findByName(@Param("name") String name);
     @Cacheable("users")
     public User findByUsername(String username);
}
