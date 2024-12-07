# kotlin-minesweeper

### 셀프 리뷰
- 좌석 예매(비행기 / 영화 등)와 동일한 도메인 요구사항이라고 생각되는데 행과 열을 어떻게 관리하는게 좋을지
  - 지금은 하나의 단위를 '셀(=좌석)'이라는 개념으로 표현했는데 행 단위로 관리(List<List<Cell>)를 하는게 더 유지보수에 대한 고민
  - -> 핵심 고민 영역: 어떤 방식이든 2차원 배열로 관리하게 될텐데 이를 어떻게 관리할지
- it에 대한 사용은 언제 해야하는가
  - O(n)으로 풀어내서 순회하는 방법이 가장 베스트겠지만, O(n^2)의 기능들은 어떻게 처리할지
  - ex) `heightRange.flatMap { height -> (widthRange).map { width -> Cell(Height(height), Width(width)) } }`
- takeIf말고 다른 방법으로 풀어낼 순 없는지
  - O(n)을 사용하면서도 minePosition의 값을 사용하도록
- lazy를 사용해서 range를 만들어 낼 순 없는지

### 기능 요구 사항

- [x] 높이, 너비, 지뢰 개수를 입력받는다.
- [x] 가급적 랜덤하게 지뢰를 배치한다

### 기능 구현 사항

- [x] 높이와 너비를 받아 List<>의 형태로 지뢰판을 만든다.
- [x] 지뢰를 랜덤하게 배치한다. (RANDOM(row, column) -> 'Cell'이라는 개념적인 모델로 관리)
- [x] 코드를 리팩터링한다.
  - [x] Mine, Mines는 현재 어떠한 역할을 하는지 명확히 되지 않아서 Cells로 통신하도록 변경
  - [x] Minesweeper가 height, width, mineCount의 상태를 가지도록 변경
  - [x] range 생성을 Minesweeper가 하는 책임으로 변경 
  - [x] Cells#placeMines를 O(n)으로 변경
- [x] 화면에 필요한 정보를 도메인 모델이 가지지 않도록 한다
  - [x] Cell#toString에 `return if (isMine) {"*"} else {"C"}를 외부로 분리`
- [x] 테스트를 작성한다.
