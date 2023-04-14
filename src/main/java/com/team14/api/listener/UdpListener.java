package com.team14.api.listener;

import com.team14.api.broadcaster.UdpBroadcaster;
import com.team14.api.constants.UdpConstants;
import com.team14.api.dto.ScoreDTO;
import com.team14.api.service.SsePublisher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLOutput;

@RequiredArgsConstructor
@Service
public class UdpListener {

    private final SsePublisher ssePublisher;

    private final UdpBroadcaster udpBroadcaster;

    @PostConstruct
    public void init() {
        new Thread(this::listen).start();
    }

    private void listen() {
        try (DatagramSocket listenerSocket = new DatagramSocket(UdpConstants.LISTENER_PORT)) {
            byte[] receiveData = new byte[1024];

            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                listenerSocket.receive(receivePacket);
                var message = this.formatMessage(new String(receivePacket.getData(), 0, receivePacket.getLength()));
                ssePublisher.send(message);
                udpBroadcaster.send(String.valueOf(message.getHitId()), UdpConstants.BROADCAST_PORT);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ScoreDTO formatMessage(String message) {
        String[] parts = message.trim().split(":");
        //  id of player transmitting;id of player hit
        return ScoreDTO.builder()
                .hitterId(Integer.parseInt(parts[0]))
                .hitId(Integer.parseInt(parts[1]))
                .build();
    }
}
