# kotlin-minesweeper

## 1단계 - 지뢰 찾기(그리기)

- 지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력 받기
- 지뢰는 `*` 표기
- 지뢰는 가급적 랜덤에 가깝게 배치

````
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
````

### MineSweeperGame

- 컨트롤러와 뷰를 연결하는 컨트롤러 및 서비스 역할

### BoardElement

- 위치 정보와 마크 정보를 관리하는 보드 요소

### Position

- 위치 정보

### PositionSelector

- 보드 요소 선택 전략

### MineInstallation

- 원하는 마크를 위치에 따라 설치

### MineBoardProvider

- 가로 세로 지뢰 보드 생성

### InputView

- 문구 및 입력값 조회

### OutputView

지뢰 찾기 게임 시작

## 2단계 - 지뢰 찾기(지뢰 개수)

- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

```text
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
0 1 2 * 2 1 1 * 1 0
0 1 * 3 * 1 1 1 1 0
0 1 1 2 1 1 0 0 0 0
1 1 0 0 0 0 0 0 0 0
* 1 0 0 0 1 1 1 0 0
1 2 1 1 0 2 * 2 0 0
0 1 * 1 0 3 * 3 1 1
0 1 1 1 0 2 * 2 1 *
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 0 0 0 0 0
```

### CountedMineBoard

- 지뢰 개수가 표시된 보드
- 안전 지대 존재 여부 검증

### CountedMineBoardProvider

- 지뢰가 설치된 보드로 지뢰 개수 세기
- 지뢰 개수가 세어진 보드 생성

### MineMark

- 세어진 갯수를 관리하는 마크 추가

## 3단계 - 지뢰 찾기(게임 실행)

- 지뢰가 없는 인접한 칸 열리기
- 지뢰 입력 추가

```text
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
open: 1, 1
0 1 C C C C C C C C
0 1 C C C C C C C C
0 1 C C C C C C C C
1 1 C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C

open: 4, 1
Lose Game.
```

### OpenStatus

- 열려진 상태 관리 추가