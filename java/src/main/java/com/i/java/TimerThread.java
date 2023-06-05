package com.i.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {

    public static void main(String[] args) {
        registerServerSocket();
    }

    public static void registerServerSocket() {
        int receivePort = 7777;
        int count =0;

        try {
            ServerSocket socket = new ServerSocket(receivePort);

            while (true) {
                System.out.println("start connecting...");
                //接收客户端请求
                Socket client = socket.accept();
                System.out.println("socket isConnected="+(client.isConnected()));

                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String str = in.readLine();
                    System.out.println("server accept client="+str);

//                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
//                    out.println("response="+count);

//                    OutputStream out = client.getOutputStream();
//                    out.write(("response="+count).getBytes());
//                    client.shutdownOutput();
//                    out.flush();

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    writer.write("response="+count);
                    writer.flush();

                    in.close();
                    writer.close();
                    count++;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
