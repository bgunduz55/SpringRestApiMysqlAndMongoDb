/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springtest1.repository;

import com.example.springtest1.models.RequestLog;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author bgund
 */
@Repository
public interface RequestLogRepository  extends MongoRepository<RequestLog, Long>{
    List<RequestLog> findByIp(@Param("ip") String ip);
}
