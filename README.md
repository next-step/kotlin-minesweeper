# kotlin-minesweeper

## [Step1] - 지뢰찾기(그리기)
### 기능 요구 사항
- [x] 사용자로부터 지뢰찾기 보드의 높이와 너비를 입력받는다.
- [x] 사용자로부터 지뢰의 개수를 입력받는다.
- [x] 입력받은 높이, 너비에 따라 지뢰찾기 보드를 생성한다.
- [x] 지뢰는 `*`로 표기하며, 무작위 위치에 배치한다.
- [x] 지뢰가 아닌 칸은 `C`로 표기한다.
- [x] 게임 보드를 출력하면, 각 칸은 지뢰(`*`) 또는 지뢰가 아닌 칸(`C`)으로 표시된다.

<br>

## [Step2] - 지뢰찾기(지뢰 개수)
### 기능 요구 사항
- [x] 높이와 너비, 지뢰 개수를 사용자로부터 입력받는다.
- [x] 지뢰는 눈에 잘 띄는 `*`로 표기한다.
- [x] 지뢰는 가급적 랜덤에 가깝게 배치한다.
- [x] 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수를 표시한다.
- [x] 사용자가 입력한 지뢰 개수에 따라 보드에 지뢰를 배치하고, 주변 지뢰의 수를 계산하여 게임 보드에 표시한다.
- [x] 개선된 게임 보드를 출력한다. 각 칸은 지뢰(`*`) 또는 주변 지뢰의 수(숫자)로 표시된다.

<br>
