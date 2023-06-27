# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)

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

### 기능 분석
- [x] 높이, 넓이, 지뢰 수는 0보다 커야 한다. - PositiveNumber
- [ ] 높이, 넓이, 지뢰 개수를 입력 받는다. - Minesweeper
- [ ] 일반 블럭 or 지뢰 - Block
- [ ] 지뢰는 랜덤으로 배치 한다. - Mines
    - 지뢰 배치
- [ ] Minesweeper 를 그린다? - Display?
