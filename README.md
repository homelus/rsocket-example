# RSocket 정리 

> RSocket 공식 홈페이지를 번역한 글 입니다. <br>
> http://rsocket.io/

RSocket 은 TCP, WebSockets, Aeron 처럼 byte stream 전송을 위한 binary protocol 입니다.

하나의 커넥션에서 비동기 메시지 전달을 통해 다음과 같은 대칭적인 상호작용 모델을 가능하게 합니다.

- request/response (단일 스트림)
- request/stream (여러개의 무한 스트림)
- fire-and-forget (무응답)
- channel (양방향 스트림)

다른 커넥션에서 오랫동안 살아있는 스트림에 대한 재시작을 허용하기 위해 세션 재개를 지원합니다. 특별히 모바일-서버 간 통신에서 네트워크 연결이 빈번하게 끊어지거나 변경되거나 재연결 될 때 유용합니다.

자세한 정보는 다음의 문서에서 찾을 수 있습니다.

- [FAQ](documents/FAQ.md) - 자주 묻는 질문
- [Motivations](documents/Motivation.md) - 왜 RSocket 을 사용할까?
- [Protocol](Protocol.md) - 프로토콜
- Implementations - 구현할 때 지원되는 Features 정보

질문에 대한 답변과 관련 개발자들과 소통하고 싶고 더 많은 RRSocket 에 관해 배우고 싶다면 커뮤니티 포럼에 참여하세요

다음은 java 로 구현한 클라이언트와 서버에 대한 간략한 예제입니다.

#### Example Java Server
```java
RSocketFactory.receive()
  .frameDecoder(Frame::retain)
  .acceptor(new PingHandler())
  .transport(TcpServerTransport.create(7878))
  .start()
  .block()
  .onClose();
```

#### Example Java Client
```java
Mono<RSocket> client =
  RSocketFactory.connect()
    .frameDecoder(Frame::retain)
    .transport(TcpClientTransport.create(7878))
    .start();

PingClient pingClient = new PingClient(client);

Recorder recorder = pingClient.startTracker(Duration.ofSeconds(1));

int count = 1_000;

pingClient
  .startPingPong(count, recorder)
  .doOnTerminate(() -> System.out.println("Sent " + count + " messages."))
  .blockLast();
```
