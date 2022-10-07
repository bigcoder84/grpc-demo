package cn.bigcoder.demo.grpc.news.client;

import cn.bigcoder.demo.grpc.news.proto.NewsProto;
import cn.bigcoder.demo.grpc.news.proto.NewsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-07
 **/
public class NewsClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext()
                .build();
        NewsServiceGrpc.NewsServiceBlockingStub blockingStub = NewsServiceGrpc.newBlockingStub(channel);
        NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder()
                .setData("新冠疫情成为过去式").build();
        NewsProto.NewsResponse response = blockingStub.list(request);
        List<NewsProto.News> result = response.getNewsList();
        for (NewsProto.News news : result) {
            System.out.println("title:" + news.getTitle());
        }
    }
}
