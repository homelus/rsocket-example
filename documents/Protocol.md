# [Protocol](http://rsocket.io/docs/Protocol)

## Status

프로토콜은 현재 최종 명세를 위한 초안입니다.
프로토콜의 현재 버전은 0.2(Major Version: 0, Minor Version: 2) 입니다.
현재 버전은 1.0 Release Candidate 로 고려되고 있습니다.
마지막 테스트는 근 시일내에 1.0 배포를 목표로 Java 와 C++ 구현체에서 진행되고 있습니다.

### 소개

비동기와 이진 경계를 가로지르는 Reactive Streams 시맨틱을 위한 애플리케이션 프로토콜을 명시합니다.
더 많은 정보를 위해 [rsocket.io](http://rsocket.io/) 를 참고하세요
RSocket 은 다음과 같은 운영 패러다임을 가정합니다.

- 일대일 통신
- 프록시되지 않은 통신. 프록시된다면 RSocket 시맨틱과 가정은 프록시 전체에 유지됩니다.
- 프로토콜에 의해 전송 프로토콜 세션에서 상태가 유지되지 않습니다.

이 문서에서 말하는 키워드는 [RFC 2119](https://tools.ietf.org/html/rfc2119) 를 따릅니다.
바이트 순서는 모든 필드에서 빅 앤디안입니다.
