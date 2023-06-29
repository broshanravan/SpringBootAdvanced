package com.mehr.SpringBootAdvanced.controllers;

import com.mehr.SpringBootAdvanced.beans.GreetingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class GreetingController {

    private MessageSource messageSource;

    public GreetingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path="/greetings")
    public String sayHello(){
       System.out.println(" Hello SpringBoot");
       return "Hello SpringBoot I am here Now";
    }

    @GetMapping(path="/greeting-bean")
    public GreetingBean sayHelloBean(){
        System.out.println(" Hello SpringBoot greeting bean");
        return new GreetingBean("Hello", "Bruce");
    }

    @GetMapping(path="/greetingsInternational")
    public String sayHelloInternationalized(){
        Locale locale= LocaleContextHolder.getLocale();
        System.out.println(" Hello SpringBoot internationalized");
        return messageSource.getMessage("good.morning.message",null,"Default message",locale);
    }

}
