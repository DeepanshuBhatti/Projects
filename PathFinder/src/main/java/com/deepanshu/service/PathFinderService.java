package com.deepanshu.service;

import java.util.concurrent.atomic.AtomicLong;

import com.deepanshu.model.PathData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathFinderService {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/getShortestPath")
    public PathData getShortestPath(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new PathData(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
