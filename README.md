# kotlin-minesweeper

## 기능 요구사항

지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.
- 지뢰가 없는 인접한 칸이 모두 열리게 된다.

## 프로그래밍 요구 사항

객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
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

## 도메인 모델

- `지뢰 찾기 게임(MineSweeper)`
  - [X] 높이, 너비, 지뢰 개수를 입력 받아 지뢰 판을 만든다.

- `지뢰 좌표 전략(MineCoordinateStrategy)`
  - `랜덤 좌표 전략(RandomMineCoordinateStrategyTest)` 
    - [X] 좌표 목록과 지뢰 개수를 입력 받아 지뢰 개수만큼 랜덤한 좌표를 반환한다.

- `지뢰 판(MineBoard)`
  - 지뢰 판 필드들과 종료 여부를 가진다.
  - [ ] 좌표를 입력받아 좌표에 해당하는 필드를 열 수 있다.
    - 지뢰가 없는 인접한 칸이 모두 열리게 된다.
    - 지뢰가 있는 필드를 열면 게임이 종료된다
  - [ ] 게임이 종료되었는 지 확인할 수 있다

- `지뢰 판 필드들(BoardFields)`
  - [X] 좌표가 주어지면 주변 필드를 반환한다.
  - [X] 지뢰 개수를 확인할 수 있다
  - [ ] 좌표를 입력받아 좌표에 해당하는 필드를 열 수 있다.

- `지뢰 판 필드(BoardField)`
  - 좌표와 열림 여부를 가진다.
  - [ ] 필드를 열 수 있다.
  - `지뢰 필드(MineField)`
  - `숫자 필드(NumberField)`
    - [X] 필드들이 주어지면 자신을 제외한 주변 8개 사각형에 포함된 지뢰 개수를 확인할 수 있다

- `좌표(Coordinate)`
  - x, y 좌표 값을 가진다.
  - [X] 높이와 너비가 주어지면 0부터 높이와 너비 만큼 좌표를 생성한다
  - [X] 주변 좌표들을 구할 수 있다
