# kotlin-minesweeper

## 지뢰 찾기(그리기)
```
지뢰 찾기를 변형한 프로그램을 구현한다.

높이와 너비, 지뢰 개수를 입력받을 수 있다.
지뢰는 눈에 잘 띄는 것으로 표기한다.
지뢰는 가급적 랜덤에 가깝게 배치한다.
```
### row
- [x] row는 0미만이 될 경우 예외가 발생한다.

### column
- [x] column는 0미만이 될 경우 예외가 발생한다.

### field type
- mine
- none

### cell
- [x] cell은 cell type을 가진다.
- [x] cell은 row와 column을 가진다.
- [x] 지뢰로 변경할 때 이미 지뢰라면 예외가 발생한다.

### mine board
- [x] 지뢰 찾기 맵은 cell들로 구성된다.
- [x] 지뢰 숫자를 받아 랜덤한 위치에 지뢰를 배치한다.
- [x] 이미 지뢰가 배치되어있는데 지뢰를 배치하려하면 예외가 발생한다.
- [x] 요청된 지뢰 갯수가 0개인 경우 예외가 발생한다.
- [x] 요청된 지뢰 갯수가 현재 cell크기보다 큰 경우 예외가 발생한다.

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
