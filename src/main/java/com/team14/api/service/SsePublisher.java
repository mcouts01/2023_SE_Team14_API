package com.team14.api.service;

import com.team14.api.dto.ScoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SsePublisher {

    private final ConcurrentHashMap<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public synchronized void addEmitter(String id, SseEmitter emitter) {
        this.emitters.put(id, emitter);
        emitter.onCompletion(() -> this.emitters.remove(id));
        emitter.onTimeout(() -> this.emitters.remove(id));
    }

    public synchronized void send(ScoreDTO score) {
        this.emitters.forEach((id, emitter) -> {
            try {
                emitter.send(score);
            } catch (IOException e) {
                log.error(e.getMessage());
                emitter.complete();
                emitters.remove(id);
            }
        });
    }
}
