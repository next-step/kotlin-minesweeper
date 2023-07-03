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
- [x] 높이, 넓이, 지뢰 개수를 입력 받는다. - Minesweeper
  - [x] 높이, 넓이 사이즈 보다 지뢰 갯수가 많을 수 없다.
- 보드를 생성한다.
  - [x] 사이즈 만큼 블럭을 생성한다.
    - [x] 일반 블럭 or 지뢰 - Block
  - [ ] 지뢰는 랜덤으로 블럭에 배치 된다 - Mines
- [ ] Minesweeper 를 그린다? - Display?
