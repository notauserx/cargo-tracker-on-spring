package com.practicalddd.cargotracker.routingservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {
    @RequestMapping("/")
    public String hello()
    {
        return "Hello world from routing service";
    }

    @RequestMapping("/name")
    public String hello(String name)
    {
        return "Hello , " + name + " from routing service";
    }
}
