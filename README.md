# kotlin-minesweeper

## 지뢰 찾기(그리기)
```
지뢰 찾기를 변형한 프로그램을 구현한다.

높이와 너비, 지뢰 개수를 입력받을 수 있다.
지뢰는 눈에 잘 띄는 것으로 표기한다.
지뢰는 가급적 랜덤에 가깝게 배치한다.
```
### height
- [x] 높이는 0이하가 될 경우 예외가 발생한다.

### width
- [x] 너비는 0이하가 될 경우 예외가 발생한다.

### field type
- mine
- none

### mine board
- [ ] 지뢰 찾기 맵은 높이와 너비를 받아 2차원 배열로 생성한다.
- [ ] 지뢰 숫자를 받아 랜덤한 위치에 지뢰를 배치한다.
- [ ] 이미 지뢰가 배치되어있는데 지뢰를 배치하려하면 예외가 발생한다.

### input view
```
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10
```
- [ ] 높이를 입력받는다.
  - 높이는 숫자여야 한다.
- [ ] 너비를 입력받는다.
  - 너비는 숫자여야 한다.
- [ ] 지뢰를 입력받는다.
  - 지뢰는 숫자여야 한다.

### output view
```
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
