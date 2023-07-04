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
- [ ] 지뢰 게임을 출력한다 - ResultView