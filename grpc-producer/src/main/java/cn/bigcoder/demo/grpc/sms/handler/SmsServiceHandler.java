package cn.bigcoder.demo.grpc.sms.handler;

import cn.bigcoder.demo.grpc.sms.proto.SmsProto;
import cn.bigcoder.demo.grpc.sms.proto.SmsServiceGrpc;
import com.google.protobuf.ProtocolStringList;
import io.grpc.stub.StreamObserver;

/**
 * 短信服务业务逻辑，用于测试GRPC服务端流式通信
 * @author: Jindong.Tian
 * @date: 2022-10-15
 **/
public class SmsServiceHandler extends SmsServiceGrpc.SmsServiceImplBase {
    @Override
    public void sendSms(SmsProto.SmsRequest request, StreamObserver<SmsProto.SmsResponse> responseObserver) {
        ProtocolStringList phoneNumberList = request.getPhoneNumberList();
        for (String phoneNumber : phoneNumberList) {
            // 生成多个响应，并逐一返回
            SmsProto.SmsResponse response = SmsProto.SmsResponse.newBuilder().setResult(request.getContent() + "," + phoneNumber + "已发送").build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}
