package cn.bigcoder.demo.grpc.news.handler;

import cn.bigcoder.demo.grpc.news.proto.NewsProto;
import cn.bigcoder.demo.grpc.news.proto.NewsServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * 新闻服务的业务逻辑，用于测试GRPC服务端一元通信
 * @author: Jindong.Tian
 * @date: 2022-10-07
 **/
public class NewsServiceHandler extends NewsServiceGrpc.NewsServiceImplBase {


    /**
     * rpc是双工协议
     *
     * @param request
     * @param responseObserver
     */
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
