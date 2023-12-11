# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)

### 프로그래밍 요구 사항
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

객체지향 생활 체조 원칙

1. 한 메서드에 오직 한 단계의 들여쓰기만 한다.
2. else 예약어를 쓰지 않는다.
3. 모든 원시 값과 문자열을 포장한다.
4. 한 줄에 점을 하나만 찍는다.
5. 줄여 쓰지 않는다(축약 금지).
6. 모든 엔티티를 작게 유지한다.
7. 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
8. 일급 컬렉션을 쓴다.
9. getter/setter/프로퍼티를 쓰지 않는다.

## 기능 요구사항
지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 실행 결과
```
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

### 기능 목록 

- InputView
  - 높이를 입력 받는다
  - 너비를 입력 받는다
  - 지뢰 개수를 입력 받는다
- MinesweeperController
  - 입력 값으로부터 지뢰 게임 보드를 생성한다 
  - `Height`: InputView 로부터 입력 받아 높이를 생성한다
    - 높이는 1이상 이어야 한다
  - `Width`: InputView 로부터 입력 받아 너비를 생성한다
    - 너비는 1이상 이어야 한다
  - `MineCount`: InputView 로부터 입력 받아 지뢰 개수를 생성한다
- MineBoardBuilder
  - PositionPicker를 받아, MineBoardSize를 정의하고, 지뢰가 설치되면 MineBoard를 생성한다
  - `MineBoardSize`
    - 높이와 너비를 프로퍼티로 가지고, 모든 행 * 열에 대한 Position을 생성한다
  - `Positions`
    -  전체 위치와 그 중 지뢰 위치에 대한 정보를 가진다
    -  PositionsBuilder : Positions 을 생성한다
      - 요청된 지뢰 개수만큼 랜덤한 위치에 지뢰 생성한다
      - MineCount: 지뢰 개수는 1이상이어야 한다
- MineBoard
  - 셀들을 관리한다
  - Positions으로부터 각 위치별 인접 지뢰 수에 대한 정보를 받아 Cell 을 생성한다
- Cell
  - 셀을 열과 행에 대한 위치 정보, Position을 가진다
    - Position
      - 열과 행에 대한 정보를 가진다
      - 자신 주변의 8개의 위치를 반환할 수 있다
- OutputView
    - 지뢰를 포함한 보드 결과를 출력한다
