# kotlin-minesweeper

## 기능 요구사항
지뢰 찾기를 변형한 프로그램을 구현한다.

* 높이와 너비, 지뢰 개수를 입력받을 수 있다.
* 지뢰는 눈에 잘 띄는 것으로 표기한다.
* 지뢰는 가급적 랜덤에 가깝게 배치한다.


## 실행결과
```text
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
C C C * C C C * C C
C C * C * C C C C C
C C C C C C C C C C
C C C C C C C C C C
* C C C C C C C C C
C C C C C C * C C C
C C * C C C * C C C
C C C C C C * C C *
C C C C C C C C C C
C C C C C C C C C C
```

## 리팩토링 항목 정리

* State 패턴 적용
  * `Close` -> `Open` 로 상태 변화가 가능하다
  * `Pin` 객체의 프로퍼티로 상태를 가지고 있는 형태로 바꿀 수 있게 한다
  * FlyWeight 패턴을 통해 메모리 낭비를 최소화 할 수 있게 한다
* `Pin` 의 역할
  * 현재 상태가 오픈 가능한지 알 수 있다
  * 지뢰인지 확인할 수 있다
  * 구현체 모두 생성되었을때, `Close` 상태로 생성된다
* `PinsInRow`
  * `PinsInRow` 라는 이름으로 클래스 명을 변경한다
  * Pin 의 메서드를 이용해서 카운트 할 수 있게 변경한다
* `TwoDimPins`
  * `Pins` 라는 객체로 클래스이름을 변경하자
  