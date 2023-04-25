package com.team14.api.broadcaster;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

@Service
public class UdpBroadcaster {
    public void send(String message, int targetPort) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress targetAddress = InetAddress.getByName("192.168.1.100");
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, targetAddress, targetPort);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
