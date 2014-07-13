# Hamcrest

Hamcrest는 jMock이라는 Mock 라이브러리 저자들이 참여해 만든 Matcher라이브러리로

테스트 표현식을 작성할 때 좀더 문맥적으로 자연스러운 문장을 만들 수 있도록 도와준다.

사이트 : <http://code.google.com/p/hamcrest/>

차례.

- [Core와 확장 패키지](#core)
- [](#)

설명.

## Core와 확장 패키지

|||
|---|---|
|org.hamcrest.core|오브젝트나 값들에 대한 기본적인 Matcher|
|org.hamcrest.beans|Java 빈과 그 값 비교에 사용되는 Matcher|
|org.hamcrest.collection|배열과 컬렉슨 Matcher|
|org.hamcrest.number|수 비교를 하기위한 Matcher|
|org.hamcrest.object|오브젝트와 클래스들을 비교하기 위한 Matcher|
|org.hamcrest.text|문자열 비교|
|org.hamcrest.xml|XML 문서 비교|

### Core

||||
|---|---|---|
|anything|어떤 오브젝트가 사용되든 일치한다고 판별|IsAnything|
|describedAs|테스트 실패 시에 보여줄 추가적인 메시지를 만들어주는 메시지 데코레이터|DescribedAs|
|equalTo|두 오브젝트가 동일한지 판별|IsEqual|
|is|내부적으로 equalTo와 동일|Is|

### Object

||||
|---|---|---|
|hasToString|toString메소드의 갑소가 일치 여부를 판별|HasString|
|instanceOf typeCompatibleWith|동일 인스턴스인지 타입비교, 동일하거나 상위 클래스,인터페이스인지 판별|IsInstanceOf, IsCompatibleType|
|notNullValue nullValue|Null 여부를 판별|IsNull|
|sameInstance|Object가 완전히 동일한지 비교 equals 비교가 아닌 주소비교|IsSame|


### Logical

||||
|---|---|---|
|allOf|Collection과 같은 오브젝트가 동일한지 판별|AllOf|
|anyOf|Collection에 하나이상 일치여부를 판별|AnyOf|
|not|서로 같지 않다|IsNot|

### Collection

||||
|---|---|---|
|array|두배열 내의 요소가 모두 일치하는지 판별|IsArray|
|hasEntry, hasKey, hasValue|맵요소에대한 포함여부 판단|IsMapContaining|
|hasItem, hasItems|특정 요소들을 포함하고 있는지 여부판단|IsCollectionContaining|
|hasItemInArray|배열 내에 찾는 대상이 들어 있는지 여부판별|IsArrayContaining|


### Number

||||
|---|---|---|
|closeTo|부동소수점에 대한 근사값내 일치여부|IsCloseTo|
|greaterThan, greaterThanEqualTo| 값비교 >, >=|OrderingComparison|
|lessThan, lessThanEqualTo|값비교, <, <=|OrderingComparison|


### Test

||||
|---|---|---|
|containsSTring|문자열이 포함되어 있는지 여부|StringContains|
|startWith|특정 문자열로 시작|STringStartWith|
|endsWith|특정 문자열로 종료|StringEndsWith|
|equalToIgnoringCase|대소문자 구분하지 않고 문자열 비교|IsEqualIgnoringCase|
|equalToIgnoringWhiteSpace|문자열 사이의 공백여부를 구분하지 않고 비교|IsEqualIgnoringWhiteSpace|
