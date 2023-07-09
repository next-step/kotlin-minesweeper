# kotlin-minesweeper

## 🚀 1단계 - 지뢰 찾기(그리기)
* 과제: https://edu.nextstep.camp/s/w5G1pCe7/ls/pCRQZlZz
* PR: https://github.com/next-step/kotlin-minesweeper/pull/319

### 기능 요구사항

지뢰 찾기를 변형한 프로그램을 구현한다.

* 높이와 너비, 지뢰 개수를 입력받을 수 있다.
* 지뢰는 눈에 잘 띄는 것으로 표기한다.
* 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 기능 목록
* [x] 높이와 너비만큼의 게임판을 생성한다. - Board
* [x] 높이, 너비, 지뢰 개수를 입력받는다. - Mine
* [x] 지뢰를 랜덤으로 배치한다. - Mine
* [x] 게임판을 출력한다. 지뢰는 *, 지뢰가 아닌 곳은 C 이다. - Board

## 🚀 2단계 - 지뢰 찾기(지뢰 개수)
* 과제: https://edu.nextstep.camp/s/w5G1pCe7/ls/BPol9H9g
* PR: TODO

### 기능 요구사항
* 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

### 기능 목록
* [x] 테스트 보드에서 0x0 위치를 기준으로 주변의 지뢰 개수는 0개이다. - Board
* [] 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다. - Board