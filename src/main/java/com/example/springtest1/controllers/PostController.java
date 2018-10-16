/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.controllers;

import com.example.springtest1.models.Post;
import com.example.springtest1.models.RequestLog;
import com.example.springtest1.models.User;
import com.example.springtest1.models.UserMo;
import com.example.springtest1.repository.PostRepository;
import com.example.springtest1.repository.RequestLogRepository;
import com.example.springtest1.repository.UserMoRepository;
import com.example.springtest1.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bgund
 */
@RestController
@RequestMapping("/mongo")
public class PostController {
    @Autowired
    PostRepository postRepository;
    
    @Autowired private MongoOperations operations;
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMoRepository userMoRepository;
    @Autowired
    RequestLogRepository requestLogRepository;
    
    @GetMapping("/posts")
    public List<Post> getAllPosts(HttpServletRequest request) {
        this.createLog(request);
        return postRepository.findAll();
    }
    @GetMapping("/post/{post}")
    public List<Post> getPostsByPost(@PathVariable(value = "post") String post, HttpServletRequest request) {
        this.createLog(request);
        return postRepository.findByPost(post);
    }
    
    @GetMapping("/username/{username}")
    public List<Post> getPostsByUsername(@PathVariable(value = "username") String username, HttpServletRequest request) {
        this.createLog(request);
        User temp = userRepository.findByUsername(username); 
        System.out.println(temp.getId());
        return postRepository.findFirst10ByUserid(temp.getId());
    }
    @GetMapping("/usernamemo/{username}")
    public List<Post> getPostsByUsernameMo(@PathVariable(value = "username") String username, HttpServletRequest request) {
        this.createLog(request);
        UserMo temp = userMoRepository.findByUsername(username); 
        System.out.println(temp.getUid());
        return postRepository.findFirst10ByUserid(temp.getUid());
    }
    @GetMapping("/postSearch/{post}")
    public List<Post> searchPostByPost(@PathVariable(value="post") String postText, HttpServletRequest request) {
        this.createLog(request);
        Query query = new Query();
        query.addCriteria(Criteria.where("post").regex(postText));
        query.limit(10);
        List<Post> products = operations.find(query, Post.class);
        return products;
    }
    
    public void createLog(HttpServletRequest req) {
        RequestLog log = new RequestLog();
        log.setIp(req.getRemoteAddr());
        log.setPath(req.getRequestURI());
        log.setParams(req.getQueryString());
        log.setCreatedAt(new Date());
        log.setRequestType(req.getMethod());
        requestLogRepository.save(log);
        
    }
    
}
