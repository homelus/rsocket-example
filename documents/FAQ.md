# [FAQ](http://rsocket.io/docs/FAQ)

### 왜 새로운 프로토콜을 사용하나요?

새로운 프로토콜을 만드는 동기에 대한 설명은 [Motivation](Motivation.md) 에서 찾아볼 수 있습니다.

몇몇 중요한 핵심 이유는 다음과 같습니다.

- request/response 를 넘어서 streaming responses, push 와 같은 모델 간의 상호작용을 지원합니다.
- 애플리케이션 레벨에서 네트워크 경계를 구분하는 흐름 제어 시멘틱(비동기적인 pull, 한정 배치 사이즈의 push)
- 단일 커넥션을 이진화, 다중화하여 사용
- 전송 커넥션에서 오래 지속되는 구독에 대한 재시작 지원
- WebSocket 과 [Aeron](https://github.com/real-logic/Aeron)와 같은 전송 프로토콜을 사용하기 위한 애플리케이션 프로토콜의 필요성

