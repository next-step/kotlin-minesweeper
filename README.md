# kotlin-minesweeper
## 1단계 - 지뢰 찾기(그리기)
### 기능 요구사항
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 구현
- [x] 입력받은 높이(height), 너비(height) 만큼 칸을 만든다.
- [x] 입력받은 지뢰 수(mineCount) 만큼 지뢰를 배치한다.
- [x] 화면에 지뢰 맵(mineMap)을 출력한다.

## 2단계 - 지뢰 찾기(지뢰 개수)
### 기능 요구사항 (추가)
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

### 구현
- [x] MineSweeper 는 기준 셀을 제외한 주변 최대 8개 셀에 포함된 지뢰의 개수 알아낸다.

## 3단계 - 지뢰 찾기(게임 실행)
### 기능 요구사항 (추가)
- 지뢰가 없는 인접한 칸이 모두 열리게 된다.

### 구현
- [x] NumberCell은 Open 여부 상태를 갖고 있으며 변경 가능하다 (초기값: false)
- [x] NumberCell Open 상태에 따라 출력을 다르게 한다. (Close: "C", Open: 숫자)
- [x] 사용자로부터 Open 위치를 입력 받을 수 있다.
  - [x] MineCell Open 시 게임은 즉시 종료 된다.
  - [x] NumberCell Open 시 게임이 종료 되지 않고, 지뢰 맵(mineMap)을 현황을 출력하며, 다음 Open 위치를 입력 받는다.
- [ ] NumberCell Open 시 주변 지뢰 숫자가 0이라면, 주변의 NumberCell을 전부 Open 한다.
