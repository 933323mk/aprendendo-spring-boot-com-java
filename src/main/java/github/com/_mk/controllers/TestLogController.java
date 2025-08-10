package github.com._mk.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/api/test/v1")
    public String testLog(){
        logger.debug("THIS IS AN DEBUG LOG");
        logger.info("THIS IS AN INFO LOG");
        logger.warn("THIS IS AN WARN LOG");
        logger.error("THIS IS AN ERROR LOG");

        return "LOG GENERATED SUCCESSFULLY!";

    }
}
