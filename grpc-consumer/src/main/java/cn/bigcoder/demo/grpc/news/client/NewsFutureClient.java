package cn.bigcoder.demo.grpc.news.client;

import cn.bigcoder.demo.grpc.news.proto.NewsProto;
import cn.bigcoder.demo.grpc.news.proto.NewsServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用
 * @author: Jindong.Tian
 * @date: 2022-10-07
 **/
public class NewsFutureClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext()
                .build();
        NewsServiceGrpc.NewsServiceFutureStub newsServiceFutureStub = NewsServiceGrpc.newFutureStub(channel);
        NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder()
                .setData("新冠疫情成为过去式").build();
        ListenableFuture<NewsProto.NewsResponse> future = newsServiceFutureStub.list(request);
        NewsProto.NewsResponse newsResponse = future.get();
        for (NewsProto.News news : newsResponse.getNewsList()) {
            System.out.println(news);
        }
    }
}
