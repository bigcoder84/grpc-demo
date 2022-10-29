# grpc-demo

grpc-demo 为grpc示例代码

## 一. 项目结构

### 1.1 原生API
- grpc-producer：grpc生产者
- grpc-consumer：grpc消费者

### 1.2 Grpc整合SpringBoot
- grpc-producer-spring：grpc生产者，grpc与SpringBoot集成，使用第三方starter
- grpc-consumer-spring：grpc消费者，grpc与SpringBoot集成，使用第三方starter

## 二. 通信模式

- 一元通信模式：从客户端发起一次请求，会产生从服务端的一次响应。参考：news.proto
- 服务端流式RPC通信模式：从客户端发起一次请求，会产生从服务端的多次响应。参考：sms.proto
- 客户端流式RPC通信模式：从客户端发起不定次请求，产生从服务端的一次响应。参考：register.proto
- 双向流式RPC通信模式：从客户端发起不定次请求，产生从服务端的不定次响应。参考：msg.proto
