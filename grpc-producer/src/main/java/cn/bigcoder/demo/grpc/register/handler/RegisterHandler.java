package cn.bigcoder.demo.grpc.register.handler;

import cn.bigcoder.demo.grpc.register.proto.RegisterProto;
import cn.bigcoder.demo.grpc.register.proto.RegisterServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * 注册服务业务逻辑，用于测试客户端流式通信
 *
 * @author: Jindong.Tian
 * @date: 2022-10-15
 **/
public class RegisterHandler extends RegisterServiceGrpc.RegisterServiceImplBase {
    @Override
    public StreamObserver<RegisterProto.RegisterRequest> register(StreamObserver<RegisterProto.RegisterResponse> responseObserver) {
        return new StreamObserver<RegisterProto.RegisterRequest>() {
            int i = 0;

            @Override
            public void onNext(RegisterProto.RegisterRequest registerRequest) {
                System.out.println(registerRequest.getIp() + " IP已注册");
                i++;
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(RegisterProto.RegisterResponse.newBuilder().setResult("您本次批量注册了" + i + "个IP地址").build());
                responseObserver.onCompleted();
            }
        };
    }
}
