# kotlin-minesweeper

* 1단계 - 지뢰 찾기(그리기)

*** 기능 요구사항

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

*** 실행 결과

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

*** 기능 목록

- [x] 높이를 입력 받는다 - InputView
- [x] 너비를 입력 받는다 - InputView
- [x] 지뢰 개수를 입력 받는다 - InputView
- [x] 지뢰위치를 생성한다 - Mines, MineGenerator
- [x] 지뢰 게임을 생성한다 - Minesweeper
- [x] 지뢰 게임을 출력한다 - ResultView


* 2단계 - 지뢰 찾기(지뢰 개수)

*** 기능 요구사항

- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

*** 실행 결과

```
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

*** 기능 목록

- [x] 각 cell의 숫자를 구한다
- [x] 각 cell의 숫자를 출력한다


* 3단계 - 지뢰 찾기(게임 실행)

*** 기능 요구사항

- 지뢰가 없는 인접한 칸이 모두 열리게 된다.

*** 실행 결과

```
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

*** 기능 목록

- [x] open 할 칸의 번호를 입력받는다 - InputView
- [x] 지뢰가 없는 인접한 칸이 모두 열리게 된다. 
- [x] open된 칸만 번호가 출력된다.
- [] 지뢰를 오픈할 때까지 게임이 계속된다
- [ ] 지뢰를 오픈하면 Lose game.이 출력된다