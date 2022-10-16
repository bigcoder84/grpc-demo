package cn.bigcoder.demo.grpc.msg.handler;

import cn.bigcoder.demo.grpc.msg.proto.MessageServiceGrpc;
import cn.bigcoder.demo.grpc.msg.proto.MsgProto;
import io.grpc.stub.StreamObserver;

/**
 * 消息服务业务逻辑，用于测试 双向流式RPC通信模式
 *
 * @author: Jindong.Tian
 * @date: 2022-10-16
 **/
public class MessageServiceHandler extends MessageServiceGrpc.MessageServiceImplBase {

    @Override
    public StreamObserver<MsgProto.MessageRequest> sendMsg(StreamObserver<MsgProto.MessageResponse> responseObserver) {

        return new StreamObserver<MsgProto.MessageRequest>() {
            @Override
            public void onNext(MsgProto.MessageRequest messageRequest) {
                System.out.println(messageRequest.getMsg() + " 成功接收。");
                responseObserver.onNext(MsgProto.MessageResponse.newBuilder().setResult(String.format("【%s】成功发送给主管", messageRequest.getMsg())).build());
                responseObserver.onNext(MsgProto.MessageResponse.newBuilder().setResult(String.format("【%s】成功发送给部门经理", messageRequest.getMsg())).build());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}