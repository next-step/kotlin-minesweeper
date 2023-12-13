# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)

### 프로그래밍 요구 사항
객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

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

객체 지향 5원칙을 지키면서 프로그래밍한다.

객체지향 5원칙(SOLID)

- SRP (단일책임의 원칙: Single Responsibility Principle): 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
- OCP (개방폐쇄의 원칙: Open Close Principle): 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
- LSP (리스코브 치환의 원칙: The Liskov Substitution Principle): 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
- ISP (인터페이스 분리의 원칙: Interface Segregation Principle): 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
- DIP (의존성역전의 원칙: Dependency Inversion Principle): 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.

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
  - [ ] 오픈할 칸의 수를 입력 받는다
- MinesweeperController
  - 입력 값으로부터 지뢰 게임 보드를 생성한다 
    - `Height`: InputView 로부터 입력 받아 높이를 생성한다
      - 높이는 1이상 이어야 한다
    - `Width`: InputView 로부터 입력 받아 너비를 생성한다
      - 너비는 1이상 이어야 한다
    - `MineCount`: InputView 로부터 입력 받아 지뢰 개수를 생성한다
      - 지뢰는 1개 이상이여야 한다
  - [ ] 게임을 시작한다
  - [ ] 게임이 시작되면 사용자로부터 오픈할 칸을 입력 받아 지뢰 게임을 진행한다
- MineBoardBuilder
  - PositionPicker를 받아, MineBoardSize를 정의하고, 지뢰가 설치되면 MineBoard를 생성한다
  - `MineBoardSize`
    - 높이와 너비를 프로퍼티로 가지고, 모든 행 * 열에 대한 Position을 생성한다
  - `Positions`
    -  전체 위치와 그 중 지뢰 위치에 대한 정보를 가진다
    -  `PositionsBuilder` : Positions 을 생성한다
      - 요청된 지뢰 개수만큼 랜덤한 위치에 지뢰 생성한다
- MineBoard
  - 셀들을 관리한다
  - Positions으로부터 각 위치별 인접 지뢰 수에 대한 정보를 받아 Cell 을 생성한다
  - [ ] cell 을 오픈한다
    - 오픈한 셀이 지뢰가 아닐 경우 인접한 칸 중 지뢰가 아닌 칸을 모두 연다
    - 오픈한 셀이 지뢰일 경우 보드를 닫는다
- Cell
  - 셀을 열과 행에 대한 위치 정보, Position을 가진다
    - `Position`
      - 열과 행에 대한 정보를 가진다
      - 자신 주변의 8개의 위치를 반환할 수 있다
- OutputView
    - 지뢰를 포함한 보드 결과를 출력한다
    - [ ] 게임 결과를 출력한다
