# kotlin-minesweeper

## STEP1 1단계 - 지뢰 찾기(그리기)

### 기능적 요구사항
- 높이와 너비, 지뢰 개수에 맞춰 MineMap 을 생성해야한다
- 지뢰를 표시하는 문자는 쉽게 바꿀수 있어야 한다

### 비 기능적 요구사항
- 지뢰배치는 외부에서 주입받을수 있어야한다


<br><br>


## 지뢰찾기 도메인 용어집

### Board : 지뢰찾기 게임이 펼쳐지는 공간
- 보드는 지뢰들의 위치(Points) 를 가지고 있음

### Point : 지뢰찾기의 칸 하나를 의미함

### Points : Point 의 일급 컬렉션. 
- 자료구조로는 Map<Cordi,Attribute> 를 가지고 있다
- 위치/좌표(Coordinate)정보와, 속성(Attribute) 정보를 포함하고 있다

### Attribute : 한칸이 갖는 속성
- 지뢰(Mine)인지, 땅인지(Ground)를 나타낸다

### Horizontal
- 수평/가로/좌$우 방향을 의미함
- 동일한 키워드로는 width, column 이 있지만, 이번 미션에서는 해당 키워드 사용을 의도적으로 배제하고 Horizontal 로 통일함
    - 수평정보 : horizontalIndex
    - 최대수평 : horizontalLimit
- 양수만 가능, 음수 불가능

## Vertical
- 수직/세로/위&아래 방향을 의미함
- 동일한 키워드로는 height, row 가 있지만, 이번 미션에서는 해당 키워드 사용을 의도적으로 배제하고 Vertical 로 통일함
    - 높이정보 : VerticalIndex
    - 최대높이 : VerticalLimit
- 양수만 가능, 음수 불가능

## Coordinate
- 이번 미션에서의 좌표계를 의미합니다
- 아래는 예시
```text
# 표시는 (Vertical, Horizontal) 순서임

(0,0)   (0,1)   (0,2)   (0,3)   (0,4)

(1,0)   (1,1)   (1,2)   (1,3)   (1,4)

(2,0)   (2,1)   (2,2)   (2,3)   (2,4)

(3,0)   (3,1)   (3,2)   (3,3)   (3,4)

(4,0)   (4,1)   (4,2)   (4,3)   (4,4)
```

## step3
- 인접한 숫자있는 칸까지 열림 :: 해당 기능에 대한 실행 로그
```text
높이를 입력하세요.
9
너비를 입력하세요.
9
지뢰는 몇 개인가요?
9
지뢰찾기 게임 시작
open : 1,1
0 0 2 C C C C C C
0 0 2 C C C C C C
0 0 1 C C C C C C
0 0 1 C C C C C C
0 0 1 C C C C C C
0 0 2 C C C C C C
0 0 1 C C C C C C
1 1 2 C C C C C C
C C C C C C C C C
open : 5,5
0 0 2 C 2 0 0 0 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 1 1 1 0
0 0 1 C C C C 1 0
0 0 1 C 1 1 1 1 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 0 0 1 1
1 1 2 C 1 1 1 3 C
C C C C C C C C C
open : 6,6
0 0 2 C 2 0 0 0 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 1 1 1 0
0 0 1 C C C C 1 0
0 0 1 C 1 1 1 1 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 0 0 1 1
1 1 2 C 1 1 1 3 C
C C C C C C C C C
open : 7,7
0 0 2 C 2 0 0 0 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 1 1 1 0
0 0 1 C C C C 1 0
0 0 1 C 1 1 1 1 0
0 0 2 C 2 0 0 0 0
0 0 1 C 1 0 0 1 1
1 1 2 C 1 1 1 3 C
C C C C C C C 3 C
open : 8,8
0 0 2 * 2 0 0 0 0
0 0 2 * 2 0 0 0 0
0 0 1 C 1 1 1 1 0
0 0 1 C C C * 1 0
0 0 1 * 1 1 1 1 0
0 0 2 C 2 0 0 0 0
0 0 1 * 1 0 0 1 1
1 1 2 C 1 1 1 3 *
C * C C C C * 3 *
Lose Game.

```
