package com.example.springtestdemo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class AzureSampleContoller {

    @GetMapping(value = "/hello")
    public String getHello(){
        return "hello world";
    }

    @PostMapping(value ="/sentQueueMsg")
    public String sendQueueMsg(@RequestParam  String msg){
        return "ok";
    }

    @PostMapping(value = "/getQueueMsg")
    public String getQueueMsg(@RequestParam  String msg){
        return "ok";
    }

}
