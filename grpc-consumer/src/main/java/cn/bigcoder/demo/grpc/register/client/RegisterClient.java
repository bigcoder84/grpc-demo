package cn.bigcoder.demo.grpc.register.client;

import cn.bigcoder.demo.grpc.register.proto.RegisterProto;
import cn.bigcoder.demo.grpc.register.proto.RegisterServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-15
 **/
public class RegisterClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext()
                .build();
        RegisterServiceGrpc.RegisterServiceStub stub = RegisterServiceGrpc.newStub(channel);

        // 服务端返回响应的监听器
        StreamObserver<RegisterProto.RegisterResponse> responseStreamObserver = new StreamObserver<RegisterProto.RegisterResponse>() {
            /**
             * 接收到服务端处理完毕的响应消息
             * @param registerResponse
             */
            @Override
            public void onNext(RegisterProto.RegisterResponse registerResponse) {
                System.out.println(registerResponse.getResult());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("处理完毕");
            }
        };

        StreamObserver<RegisterProto.RegisterRequest> requestStreamObserver = stub.register(responseStreamObserver);

        for (int i = 1; i <= 10; i++) {
            RegisterProto.RegisterRequest request = RegisterProto.RegisterRequest.newBuilder().setIp("192.169.0." + i).build();
            requestStreamObserver.onNext(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestStreamObserver.onCompleted();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
