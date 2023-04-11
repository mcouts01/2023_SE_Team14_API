package com.team14.api.service;

import com.team14.api.dto.ScoreDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SsePublisher {

    private final List<SseEmitter> emitters = new ArrayList<>();

    public synchronized void addEmitter(SseEmitter emitter) {
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
    }

    public synchronized void removeEmitter(SseEmitter emitter) {
        this.emitters.remove(emitter);
    }

    public synchronized void send(ScoreDTO score) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(score);
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.removeIf(deadEmitters::contains);
    }
}
