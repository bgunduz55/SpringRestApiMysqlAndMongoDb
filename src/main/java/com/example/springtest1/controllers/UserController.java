/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.controllers;

import com.example.springtest1.models.RequestLog;
import com.example.springtest1.models.User;
import com.example.springtest1.repository.RequestLogRepository;
import com.example.springtest1.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author bgund
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RequestLogRepository requestLogRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers(HttpServletRequest request) {
         this.createLog(request);
        return userRepository.findAll();
    }
    
@GetMapping("/user/{id}")
public Optional<User> getUserById(@PathVariable(value = "id") Long userId, HttpServletRequest request) {
    this.createLog(request);
    return userRepository.findById(userId);
}
@GetMapping("/userbyname/{name}")
public List<User> getUserByName (@PathVariable(value = "name") String username, HttpServletRequest request) {
     this.createLog(request);
    return userRepository.findByName(username);
} 

@GetMapping("/userbyusername/{name}")
public User getUserByUsername (@PathVariable(value = "name") String username, HttpServletRequest request) {
     this.createLog(request);
    return userRepository.findByUsername(username);
} 

@RequestMapping(value = "processing", method = RequestMethod.GET)
public @ResponseBody String processData(HttpServletRequest request) {
    
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader("Proxy-Client-IP"));
        System.out.println(request.getRemoteHost());
        System.out.println(request.getRequestURI());
        System.out.println(request.getMethod());
        // some other code
        return "success";
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
