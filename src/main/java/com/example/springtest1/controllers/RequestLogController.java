/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.controllers;

import com.example.springtest1.models.RequestLog;
import com.example.springtest1.repository.RequestLogRepository;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bgund
 */
@RestController
@RequestMapping("/logs")
public class RequestLogController {

    @Autowired
    RequestLogRepository requestLogRepository;

    @GetMapping("/all")
    public List<RequestLog> getAllLogs(HttpServletRequest request) {
        this.createLog(request);
        return requestLogRepository.findAll();
    }

    @GetMapping("/ip/{ip}")
    public List<RequestLog> getAllLogs(@PathVariable(value = "ip") String ip, HttpServletRequest request) {
        this.createLog(request);
        return requestLogRepository.findByIp(ip);
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
