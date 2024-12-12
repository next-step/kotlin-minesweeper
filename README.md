# kotlin-minesweeper

## Step 1 

### 기능 요구사항
#### 지뢰 찾기를 변형한 프로그램을 구현한다.
- [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- [x] 지뢰는 눈에 잘 띄는 것으로 표기한다.
- [x] 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 실행 결과
```declarative
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
#### 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
- 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- else 예약어를 쓰지 않는다.
- 모든 원시 값과 문자열을 포장한다.
- 한 줄에 점을 하나만 찍는다.
- 줄여 쓰지 않는다(축약 금지).
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 일급 컬렉션을 쓴다.
- getter/setter/프로퍼티를 쓰지 않는다.

### 기능 구현 사항
#### Domain
- [x] Cell
  - [x] 지뢰의 상태를 가진다.
- [x] Cells
  - [x] Cell의 2차원 Map을 가진다.
- [x] Board
  - [x] 지뢰를 배치할 수 있도록 한다.
- [x] Column
  - [x] Cell의 가로 리스트
- [x] Width
  - [x] Board의 가로 길이
  - [x] 0보다 큰 값을 가진다.
- [x] Height
  - [x] Board의 세로 크기
  - [x] 0보다 큰 값을 가진다.
- [x] MineCount
  - [x] 지뢰 개수
  - [x] 양수를 가진다.
  - [x] Board의 Cell 개수 보다  클수는 없다.
- [x] CellState
  - [x] 지뢰를 가졌거나, 주변의 지뢰의 개수를 표시한다.


#### Presentation
- InputView
  - [x] 높이, 넓이, 지뢰개수를 입력받는다.
- ResultView
  - [x] 지뢰보드를 출력한다.


#### Controller
- MinesWeeperGame
  - [x] 지뢰찾기 게임을 컨트롤 한다.