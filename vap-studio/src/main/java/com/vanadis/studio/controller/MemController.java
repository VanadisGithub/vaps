package com.vanadis.studio.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.vanadis.lang.file.FileUtils;
import com.vanadis.studio.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Mem
 *
 * @author yaoyuan
 * @date 2023/1/28 17:24
 */
@RestController
@RequestMapping("")
public class MemController {

    @Autowired
    private Data data;

    @GetMapping("write")
    public void write(String text) {
        data.write(text, text);
    }

    @GetMapping("read")
    public JSONObject read() {
        return data.read();
    }

    @GetMapping("get")
    public JSONObject get() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(3000);
//        httpRequestFactory.setConnectTimeout(3000);
//        httpRequestFactory.setReadTimeout(3000);
//        RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        return restTemplate.getForObject("http://127.0.0.1:8080/get1", JSONObject.class);
    }

    @GetMapping("get1")
    public JSONObject get1() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            Thread.sleep(10000);
            System.out.println(i + 1);
        }
        return new JSONObject();
    }

    private final List<String> dynamicStrings = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));

    @GetMapping("/flux1")
    public Flux<String> getInitialFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .zipWith(Flux.fromIterable(dynamicStrings), (i, s) -> s);
    }

    @GetMapping("/flux2")
    public Flux<String> getDynamicFlux() {
        Flux<String> flux = Flux.fromIterable(dynamicStrings);
        dynamicStrings.remove(0);
        dynamicStrings.add(String.valueOf(System.currentTimeMillis()));
        return flux;
    }

    @GetMapping("/reactive")
    public Mono<List<String>> getReactiveData() {
        Mono<String> mono1 = Mono.delay(Duration.ofSeconds(2)).map(i -> "Result 1");
        Mono<String> mono2 = Mono.delay(Duration.ofSeconds(8)).map(i -> "Result 2");
        Mono<String> mono3 = Mono.delay(Duration.ofSeconds(16)).map(i -> "Result 3");
        System.out.println(1);
        return Mono.zip(mono1, mono2, mono3)
                .map(tuple -> Arrays.asList(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }
}
