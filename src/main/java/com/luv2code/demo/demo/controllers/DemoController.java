package com.luv2code.demo.demo.controllers;
import com.luv2code.demo.demo.configs.CoachConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Testing
 *
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CoachConfig coachConfig;

    @Value("${coach.name}")
    private String coachName;

    @GetMapping("/hello")
    public String hello(){
        logger.info("coach" + coachConfig.getName()+ "  " + coachConfig.getUserId());
        return "hello: " + coachName;
    }
}
