package com.aleenjava.barry.ddd.infrastructure.client;


import org.springframework.stereotype.Component;

@Component
public class LocalAuthClient implements AuthFeignClient{
    @Override
    public Integer findByUserName(String userName) {
        return 100;
    }
}
