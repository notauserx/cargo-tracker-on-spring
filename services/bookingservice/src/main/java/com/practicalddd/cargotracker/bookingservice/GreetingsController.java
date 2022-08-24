package com.practicalddd.cargotracker.bookingservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {
    @RequestMapping("/")
    public String hello()
    {
        return "Hello world. Again";
    }

    @RequestMapping("/name")
    public String hello(String name)
    {
        return "Hello world," + name;
    }
}
