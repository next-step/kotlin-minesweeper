# kotlin-minesweeper

## Step1 - 지뢰찾기
### 기능 요구사항
지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.


### 실행 결과
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

### 프로그래밍 요구 사항
* 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

객체지향 생활 체조 원칙
- 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- else 예약어를 쓰지 않는다.
- 모든 원시 값과 문자열을 포장한다.
- 한 줄에 점을 하나만 찍는다.
- 줄여 쓰지 않는다(축약 금지).
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 일급 컬렉션을 쓴다.
- getter/setter/프로퍼티를 쓰지 않는다.

### 상세 구현 사항
[] 높이를 입력받는다.
    [x] empty이거나 숫자가 아니면 IllegalArgumentException을 반환한다 
    [x] 0을 넘지 않으면 IllegalArgumentException을 반환한다
[] 너비를 입력받는다.
    [x] empty이거나 숫자가 아니면 IllegalArgumentException을 반환한다
    [x] 0을 넘지 않으면 IllegalArgumentException을 반환한다
[] 지뢰 개수를 입력받는다.
    [x] empty이거나 숫자가 아니면 IllegalArgumentException을 반환한다
    [x] 0을 넘지 않으면 IllegalArgumentException을 반환한다
    [x] 높이 * 너비 보다 큰 숫자이면 IllegalArgumentException을 반환한다
[] 지뢰 찾기
    [] 보드를 생성한다
    [] 지뢰를 생성한다
    [] 일반 땅을 생성한다
[] 지뢰 결과를 출력한다.
    [] 지뢰와 아닌것을 구분하여 출력한다.
