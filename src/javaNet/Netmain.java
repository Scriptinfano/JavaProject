package javaNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Netmain {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(25);
            Socket newSocket = server.accept();//服务器监听请求，如果监听到请求则返回一个socket对象以便完成数据传输
            BufferedReader newBufferReader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));

            while (true) {
                String newLine = newBufferReader.readLine();
                if (newLine.equals("exit")) {
                    break;
                }
                System.out.println("received from client:" + newLine);
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
