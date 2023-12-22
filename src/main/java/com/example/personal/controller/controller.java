package com.example.personal.controller;

import com.example.personal.domain.info;
import com.example.personal.service.dataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class controller {
    @Autowired
    public dataservice dataservice;
    @PostMapping
    public String save(@RequestBody String url){
        try {
            info info = new info();
            String[] split = url.split("&");
            info.setName(split[0].split("=")[1]);
            info.setEmail(split[1].split("=")[1]);
            info.setMessage(split[2].split("=")[1]);
            dataservice.saveAll(info);
        } catch (Exception e) {
            return "Time out, please send again";
        }
        return "I have received your message!";
    }
}
