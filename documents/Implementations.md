# [Implementations](http://rsocket.io/docs/Implementations)

## Mandatory Core Protocol Features

드라이버들은 [Protocol](Protocol.md) 문서에 정의된 모든 핵심 특징들을 구현하도록 가정됩니다. 핵심 범위에 다음이 포함되며 
여기에 국한되지는 않습니다.

- 메타데이터와 페이로드 프레임
- 4개의 통신 모델: Fire-and-forget, 응답/요청, 요청 스트림, 요청 채널
- 요청-N 프레임: 애플리케이션 레벨의 흐름 제어
