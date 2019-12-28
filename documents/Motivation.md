# Motivations

> [본문 참조](http://rsocket.io/docs/Motivations)

대규모 분산 시스템들은 때때로 다양한 기술과 프로그래밍 언어를 사용해 다른 팀에 의해 모듈방식으로 구현됩니다.
작은 조각들은 안정적으로 의사소통하고 신속하고 변화에 독립적으로 지원되어야 합니다.
모듈간 효율적이고 확장성있는 통신은 분산 시스템에서 중요한 관심사이다. 
사용자에 대한 지연량과 얼마만큼의 시스템을 빌드하고 동작시키는데 요구되는 자원의 양이 얼마나 되는지는 중요한 관심사이다.

[Reactive Menifesto](https://www.reactivemanifesto.org/) 에 문서화 되어 있고 
[Reactive Streams](http://www.reactive-streams.org/) 와 [Reactive Extensions](http://reactivex.io/) 와 같은 라이브러리에 구현되어 있는 구조적인 패턴은 비동기 메시지 구조를 선호하고 
request/response 을 이외의 상호작용 패턴을 수용합니다.

"RSocket" 프로토콜은 "reactive" 원칙을 수용하는 공식적인 통신 프로토콜입니다.

다음은 새로운 프로토콜을 정의하기 위한 동기가 된 항목들입니다.

### Message Driven
네트워크 통신은 비동적입니다. RSocket 프로토콜은 이를 수용하고 모든 통신을 단일 네트워크 연결에서 다중화 된 메시지 스트림을 모델링하며
응답을 기다리는 동안 차단하지 않습니다.

[Reactive Manifesto](http://www.reactivemanifesto.org/) 는 다음과 같이 말합니다.
> Reactive Systems 는 비동기 메시지 전달에 의존하여 약한 결합, 고립성, 지역성, 투명성을 보장하는 구성요소 간 경계를 설정하고
> 오류를 메시지로 위임하는 수단을 제공합니다. 명시적인 메시지 전달을 사용하는 것은 필요할 때 배압을 적용하고 시스템에서 메시지 큐를 
> 만들거나 모니터링하여 적재 관리, 탄력성, 흐름 제어를 가능하게 합니다.
> ... Non-blocking 은 수신인이 적은 시스템 오버헤드를 받고 활성화 될때만 자원을 소비하도록 도와줍니다.

또한 [HTTP/2 FAQ](https://http2.github.io/faq/#why-is-http2-multiplexed) 에서는 지속적인 연결에서 멀티플렉싱 형태로 메시지 지향 프로토콜의 채택 동기를 설명하는데 도움을 줍니다.

> HTTP/1.x 는 "head-of-line blocking" 이라고 불리는 문제를 가지고 있어 연결에서 한번에 하나의 요청만 효과적으로 처리할 수 있습니다.

> HTTP/1.1 에서는 파이프라인을 사용해 고치려고 시도했지만 완벽히 해결되지 않았습니다. (크거나 느린 응답은 여전히 다른 사용자를 차단할 수 있었다)
> 또한 많은 중계자나 서버가 정확하게 처리하지 못했기 때문에 파이프라이닝 배포가 너무 어려웠습니다.

> 이로인해 클라이언트는 몇몇 휴리스틱(종종 추측)을 사용해 언제 어떤 커넥션을 오리진에 요청할 지 결정합니다.

### Interaction Models

### Fire-andForget

### Request/Response(single-response)

### Request/Stream (multi-response, finite)
 
### Channel

### Behaviors

### Resumability

### Application Flow Control

### Ployglot Support

### Transport Layer Flexibility

### Efficiency & Performance

## Comparisons

### TCP & QUIC

### WebSockets

#### HTTP/1.1 & HTTP/2
