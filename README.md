# kotlin-minesweeper

### 기능 요구사항

지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 기능 구현 목록

- [] 블록(Block)
    - [] 일반, 마인 상태를 가진다.
    - [] 본인의 위치(Point)를 가진다.
- [] 블록 열(BlockRow)
  - [] 블록들을 가진다.
  - [] 블록열의 길이는 입력된 width 동일하다.
- [] 블록 맵(BlockMap)
    - [] 콘솔 창으로 부터 입력 받은 width, height, mineCount 를 가진다.
    - [] 입력된 height 수 만큰 블록 열(길이 width)을 가진다.
    - [] 랜덤 지뢰생성기를 통해 mineCount 만큼 랜덤한 위치에 블록을 지뢰로 설정한다.
- [] 랜덤 지뢰생성기 (RandomMineGenerator)
  - [] 입력된 width, height, mineCount 에 맞는 랜덤한 지뢰 위치를 생성한다.
- [] 입력 창(InputView)
  - [] 콘솔 창으로 부터 width, height 입력을 받는다.
  - [] 콘솔 창으로 부터 mineCount 입력을 받는다.
- [] 블록 출력기(ResultView)
  - [] 블록을 출력한다.
  - [] 블록의 상태에 따라 출력을 달리한다.
