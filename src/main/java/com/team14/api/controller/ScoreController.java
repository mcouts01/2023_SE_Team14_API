package com.team14.api.controller;

import com.team14.api.service.SsePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/score-feed")
@RequiredArgsConstructor
public class ScoreController {

    private final SsePublisher ssePublisher;

    @GetMapping("/")
    public SseEmitter getScores() {
        SseEmitter emitter = new SseEmitter();
        ssePublisher.addEmitter(emitter);
        return emitter;
    }
}
