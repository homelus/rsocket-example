# [FAQ](http://rsocket.io/docs/FAQ)

### 왜 새로운 프로토콜을 사용하나요?

새로운 프로토콜을 만드는 동기에 대한 설명은 [Motivation](Motivation.md) 에서 찾아볼 수 있습니다.

몇몇 중요한 핵심 이유는 다음과 같습니다.

- request/response 를 넘어서 streaming responses, push 와 같은 모델 간의 상호작용을 지원합니다.
- 애플리케이션 레벨에서 네트워크 경계를 구분하는 흐름 제어 시멘틱(비동기적인 pull, 한정 배치 사이즈의 push)
- 단일 커넥션을 이진화, 다중화하여 사용
- 전송 커넥션에서 오래 지속되는 구독에 대한 재시작 지원
- WebSocket 과 [Aeron](https://github.com/real-logic/Aeron)와 같은 전송 프로토콜을 사용하기 위한 애플리케이션 프로토콜의 필요성

### Why not make do with XYZ?

궁극적으로 위와 같은 모든 동기는 충분한 노력으로 성취될 수 있습니다. 이 프로젝트를 시작하는 것과 더 명확하고 공식화 되기를 원했습니다.
다시 말해서 해킹보다는 솔루션이 필요했습니다.

### Why not HTTP/2?

HTTP/2는 브라우저 및 요청/응답 문서 전송이 HTTP/1 브라우저에서 더 좋지만 불행히도 요청/응답 이외의 상호작용 모델은 외부로 공개하거나, 
애플리케이션 흐름제어를 지원하지 않습니다.

여기 몇몇 HTTP/2 명세로부터의 인용과 HTTP/2 가 타겟팅한 컨텍스트를 제공하기 유용한 FAQ 가 있습니다.

> HTTP 의 존재 의미는 그대로 남아 있습니다.

> 애플리케이션 관점에서 모든 프로토콜의 특징들은 크게 변하지 않습니다.

> 이러한 노력은 연결된 프로토콜(HTTP의 의미를 바꾸지 않고 연결위에서 HTTP headers, methods 등을 어떻게 넣을지)의 개정을 위해 허용되었습니다.

또한 "push promises" 는 표준 웹 탐색 동작을 위한 브라우저 캐시를 입력하는데 집중되었습니다.

> 푸시 된 응답은 언제나 클라이언트의 명시적 요청과 연관되어 있습니다.

이것이 의미하는 것은 우리는 여전히 push 를 위해 SSE 와 WebSockets 가 필요하다는 것 입니다.

HTTP/2 는 HTTP/1.1 보다 웹 사이트를 위한 브라우저의 문서 검색에서 주로 낫다고 생각되어집니다.

### What about QUIC?

QUIC은 이용성 측면에서 이 시점에 많이 알려지지 않았고 사용하기에 조금 이릅니다. 만약 변경된다면 RSocket 의 전송계층으로 사용하려고 합니다.

RSocket 은 특히 QUIC 와 같은 기술위에 레이어되도록 의도되었습니다.
QUIC 는 신뢰할만한 전송과 혼잡제어, 바이트 단위의 흐름제어, 다중 바이트 스트림을 제공합니다.
이진 프레이밍과 메시지 스트림으로 동작하는 시맨틱(단방향 및 양방향),  메시지 수준 흐름 제어, 재시작 위에 RSocket 레이어가 존재합니다.

RSocket 명세는 TCP 같은 프로토콜에서 RSocket에 프레임 길이와 스트림 ID들이 포함되기 위해 레이어링 하는 것을 염두에 두고 작성되었습니다.
하지만 HTTP/2 또는 QUIC와 같은 경우 RSocket은 이를 생략하고 HTTP/2 또는 QUIC 가 제공하는 것을 사용합니다.

["RSocket Protocol: Framing Format"](https://github.com/rsocket/rsocket/blob/master/Protocol.md#framing-format) 을 참고하세요.

> 호환하는 프레이밍을 제공하지 않는 전송 프로토콜을 사용하는 경우 프레임 길이는 앞에 RSocket Frame 을 추가해야 합니다.

그리고 ["RSocket Protocol: Frame Header Format"](https://github.com/rsocket/rsocket/blob/master/Protocol.md#frame-header-format)
을 참고하세요.

> HTTP/2 와 같은 역 다중화를 포함하는 전송 프로토콜은 모든 사용자들이 동의하면 Stream ID 필드를 생략할 수 있습니다.
> 동의와 협상의 의미는 전송 프로토콜에 따릅니다.

### Why "Reactive Streams" request(n) Flow Control?

(byte 단위가 아닌) 실행 작업 단위의 측면에서 애플리케이션 피드백이 없으면 "head of line blocking" 이 발생하거나 network 나 application buffers가 점유되거나 서버에서 클라이언트가 처리할 수 있는 것 보다 더 많은 데이터가 생성되기가 쉽습니다. 하나의 스트림이 다른 모든 스트림을 점유할 수 있는 상황에서 단일 커넥션에서 여러개의 스트림들 다중화하는 것은 특히 좋지 않습니다. 애플리케이션 레이어에서 `request(n)` 는 소비자가 각 스트림에서 수신할 수 있는 양을 신호로 보내고, 생산자가 여러 스트림을 한번에 교차로 생산하는 것을 허용합니다.

다음은 TCP 를 사용하고 흐름 제어에만 의존할 때 발생할 수 있는 일부 문제에 대한 자세한 내용입니다.



