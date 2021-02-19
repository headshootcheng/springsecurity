package com.luv2code.demo.demo.controllers;
import com.luv2code.demo.demo.configs.CoachConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/** Testing
 *
 */
@RestController
@RequestMapping("/")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CoachConfig coachConfig;

    @Autowired
    private MessageSource messageSource;
//
//    @Value("${coach.name}")
//    private String coachName;
//
//    @GetMapping("/coach")
//    public String coach(){
//        logger.info("coach" + coachConfig.getName()+ "  " + coachConfig.getUserId());
//        return "hello: " + coachName;
//    }
//
    @GetMapping("/hello")
    public String hello(){
        return messageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
    }
}
