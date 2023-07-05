# kotlin-minesweeper

# step1 요구 사항 구현 체크리스트

* MinesWeeperGameBoard

- [x] 게임판은 높이와 너비를 입력받아서 2차원 배열의 게임판을 만든다.

* GameBoardValidator

- [x] 높이가 음수가 되면 IllegaArgumentException을 throw 한다.
- [x] 너비가 음수가 되면 IllegaArgumentException을 throw 한다.
- [x] 지뢰의 갯수가 음수가 되면 IllegaArgumentException을 throw 한다.
- [x] 지뢰의 갯수가 전체 게임판 보다 크면 IllegaArgumentException을 throw 한다.


* MineLocationGenerator

- [ ] 지뢰의 위치를 생성한다.

* MineLocationValidator

- [x] 지뢰의 위치가 중복된 곳이 있는지 확인한다.
- [x] 게임판의 인덱스를 벗어난 지뢰의 위치를 생성하면 IllegaArgumentException을 throw 한다.
