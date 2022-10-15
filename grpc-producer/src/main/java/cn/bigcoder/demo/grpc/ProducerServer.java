package cn.bigcoder.demo.grpc;

import cn.bigcoder.demo.grpc.news.handler.NewsServiceHandler;
import cn.bigcoder.demo.grpc.register.handler.RegisterHandler;
import cn.bigcoder.demo.grpc.sms.handler.SmsServiceHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-07
 **/
public class ProducerServer {

    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT)
                .addService(new NewsServiceHandler())
                .addService(new SmsServiceHandler())
                .addService(new RegisterHandler())
                .build().start();
        System.out.println("ProducerServer start success,port:" + PORT);
        server.awaitTermination();
    }
}
