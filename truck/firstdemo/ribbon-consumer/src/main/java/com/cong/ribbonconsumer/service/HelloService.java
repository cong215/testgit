package com.cong.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        ResponseEntity response = restTemplate.getForEntity("http://config-server/hello", String.class);
        return String.valueOf(response.getBody());
    }

    public String helloFallback() {
        return "error";
    }

}
