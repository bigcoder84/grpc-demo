package cn.bigcoder.demo.grpc.msg.client;

import cn.bigcoder.demo.grpc.msg.proto.MessageServiceGrpc;
import cn.bigcoder.demo.grpc.msg.proto.MsgProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * 双向流式通信
 * @author: Jindong.Tian
 * @date: 2022-10-16
 **/
public class MsgClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext()
                .build();
        MessageServiceGrpc.MessageServiceStub stub = MessageServiceGrpc.newStub(channel);


        // 服务端返回响应的监听器
        StreamObserver<MsgProto.MessageResponse> responseStreamObserver = new StreamObserver<MsgProto.MessageResponse>() {
            /**
             * 接收到服务端处理完毕的响应消息
             * @param messageResponse
             */
            @Override
            public void onNext(MsgProto.MessageResponse messageResponse) {
                System.out.println(messageResponse.getResult());
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

        StreamObserver<MsgProto.MessageRequest> requestStreamObserver = stub.sendMsg(responseStreamObserver);
        for (int i = 0; i < 10; i++) {
            MsgProto.MessageRequest request = MsgProto.MessageRequest.newBuilder().setMsg("消息" + i).build();
            requestStreamObserver.onNext(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestStreamObserver.onCompleted();
    }
}
