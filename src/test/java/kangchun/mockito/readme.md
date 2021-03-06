# Mockito

차례.

- [Mockito의 특징](#Mockito의 특징)
- [Mock 기본 사용법](#Mock 기본 사용법)

Mokito는 최근 떠오르고 있는 Mock 프레임워크이고 역사는 오래되지 않았지만 빠르게 확산되고 있으며,

TDD 개발자들이 주로 사용했던 방식인 상태 기반의 테스트를 지향한다는 점에서 가장인기 있는 Test 프레임워크이다.

## Mockito의 특징 

Mockito 개발자인 Szczepan Faber가 사이트에 소개한 Mock 프레임워크의 특징

### Mock 프레임워크는 어때야 하는가.

1. 단순해야한다.
2. DSL (Domain-Specific Lanuage)로 만들지 말자. 복잡해진다.
3. 문자열을 메소드 대신 사용하지 말자.
4. 읽기 어려운 Inner Class를 사용하지 말자
5. 리팩토링이 어려워서는 안된다.

### Mock 프레임워크 차별점

1. 테스트 그 자체에 집중하자.
2. 테스트 스텁을 만드는 것과 검증을 분리
3. Mock 만드는 방법을 단일화
4. 테스트 스텁을 만들기 ㅜ십다.
5. API가 간단한다.
6. 프레임워크가 지원해주지 않으면 안되는 코드를 최대한 배제
7. 실패 시에 발생하는 에러추적이 깔끔핟.

## Mock 기본 사용법

| Class           | Description |
| --------------- | --------------- |
| CreateMock      | 인터페이스에 해당하는 Mock 객체   |
| Stub            | 테스트에 필요한 Mock 객체의 동작을 지정  |
| Exercise        | 테스트 메소드 내에 Mock 객체를 사용  |
| Verify          | 메소드가 예상대로 호출됐는지 검증 |
| spy             | 테스트에 필요한 실객체를 감시자를 이용해 동작을 지정|

### verify 검증 메소드

|||
|--- |--- |
|times |n번 호출됐는지 확인 |
|never |호출되지 않았어야 함 |
|atLeastOnce |최소 한번 호출됐어야 함 |
|atLeast(n) |적어도 n번 호출됐어야 함 |
|atMost(n) |최대 n번 이상 호출되면 안됨 |

### Argument Matcher

|||
|---|---|
|any Type|anyInteger(), anyString() etc Java타입에 해당하는 any 시리즈 |
|anyCollection| ㅣList, Map, Set etc Collection 객체이면 무방 |
|argTaht|Mockit도 Hamcrest Matcher를 사용하고 있음|
|isNull| Null이면 Ok |
|isNotNull| Null아니면 Ok |


_* 참고출처 1. 테스트 주도개발 TDD 실천법과 도구 ( 채수원 지음 )_
_* 참고출처 2. https://code.google.com/p/mockito/wiki/MockitoFeaturesInKorean_