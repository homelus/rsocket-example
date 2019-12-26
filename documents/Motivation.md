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
