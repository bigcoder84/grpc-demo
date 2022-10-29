package cn.bigcoder.demo.grpc.controller;

import cn.bigcoder.demo.grpc.proto.NewsProto;
import cn.bigcoder.demo.grpc.proto.NewsServiceGrpc;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jindong.Tian
 * @date: 2022-10-29
 **/
@RestController
public class TestWeb {

    // 与配置文件中 grpc.client.grpc-server 的配置对应
    @GrpcClient("grpc-server")
    private NewsServiceGrpc.NewsServiceBlockingStub newsServiceStub;

    @GetMapping("/test")
    public String test(String data) {
        NewsProto.NewsRequest request = NewsProto.NewsRequest.newBuilder().setData(data).build();
        NewsProto.NewsResponse response = newsServiceStub.list(request);
        return JSONObject.toJSONString(response.getNewsList());
    }
}
