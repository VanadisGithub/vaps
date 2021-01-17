package com.vanadis.future.controller;

import com.vanadis.lang.result.APITemplate;
import com.vanadis.lang.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
public class HomeController {

    @GetMapping(path = {"/", "/home"})
    public String home() throws InterruptedException {
        //RestTemplate restTemplate = new RestTemplate();
        //String forObject = restTemplate.getForObject("http://127.0.0.1:8080/page", String.class);
        //System.out.println(forObject);
        return "<a href=\"/page\"><h1>Hello!</h1></a>";
    }

    @GetMapping("page")
    public String page() throws InterruptedException {
        return "<h1>Page!</h1>";
    }

    @GetMapping("test")
    public R<String> test() throws InterruptedException {
        return new APITemplate<String>() {

            @Override
            protected void checkParams() throws IllegalArgumentException {

            }

            @Override
            protected String process() throws Exception {
                return null;
            }
        }.execute();
    }

}



