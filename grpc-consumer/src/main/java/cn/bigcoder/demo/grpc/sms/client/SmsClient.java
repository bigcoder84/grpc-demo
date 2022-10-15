package cn.bigcoder.demo.grpc.sms.client;

import cn.bigcoder.demo.grpc.news.proto.NewsServiceGrpc;
import cn.bigcoder.demo.grpc.sms.proto.SmsProto;
import cn.bigcoder.demo.grpc.sms.proto.SmsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-15
 **/
public class SmsClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext()
                .build();
        SmsServiceGrpc.SmsServiceBlockingStub smsService = SmsServiceGrpc.newBlockingStub(channel);
        Iterator<SmsProto.SmsResponse> responses = smsService.sendSms(SmsProto.SmsRequest.newBuilder()
                .setContent("今天天气不错")
                .addPhoneNumber("190000001")
                .addPhoneNumber("190000002")
                .addPhoneNumber("190000003")
                .addPhoneNumber("190000004")
                .addPhoneNumber("190000005")
                .addPhoneNumber("190000006")
                .addPhoneNumber("190000007")
                .build());
        while (responses.hasNext()) {
            SmsProto.SmsResponse response = responses.next();
            System.out.println(response.getResult());
        }
    }
}
