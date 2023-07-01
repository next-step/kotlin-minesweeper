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
- [ ] 너비는 0이하가 될 경우 예외가 발생한다.

### mine
- [ ] 지뢰는 0이하가 될 경우 예외가 발생한다.
  - 지뢰가 하나도 없을 수 없다.

### mine board
- [ ] 지뢰 찾기 맵은 가로와 세로를 받아 2차원 배열로 생성된다.
- [ ] 지뢰가 있는 위치를 랜덤하게 저장한다.

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
