package cn.bigcoder.demo.grpc.handler;

import cn.bigcoder.demo.grpc.proto.NewsProto;
import cn.bigcoder.demo.grpc.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-29
 **/
@GrpcService
public class NewsServiceHandler extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void list(NewsProto.NewsRequest request, StreamObserver<NewsProto.NewsResponse> responseObserver) {
        String data = request.getData();
        NewsProto.NewsResponse response = null;
        try {
            NewsProto.NewsResponse.Builder newListBuilder = NewsProto.NewsResponse.newBuilder();
            for (int i = 0; i < 100; i++) {
                NewsProto.News news = NewsProto.News.newBuilder()
                        .setId(i)
                        .setContent(data + "当日新闻内容" + i)
                        .setTitle("新闻标题" + i)
                        .setCreateTime(System.currentTimeMillis())
                        .build();
                newListBuilder.addNews(news);
            }
            response = newListBuilder.build();
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
}
