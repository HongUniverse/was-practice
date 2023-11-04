package org.example.was;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CusomWebApplicaionServer] started {} port", port);

            Socket clientSocket;
            logger.info("[CusomWebApplicaionServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CusomWebApplicaionServer] client connected");

                //사용자의 요청이 올때마다 스레드 생성 -> 독립적인 스택 메모리 공간을 할당받으므로 요청이 몰리게 되면 성능이 떨어지게 된다.
                //new Thread(new ClientRequestHandler(clientSocket)).start();

                //ThreadPool을 적용하여 안정적인 서비스가 가능하도록 한다.
                executorService.execute(new ClientRequestHandler(clientSocket));

            }
        }
    }
}
