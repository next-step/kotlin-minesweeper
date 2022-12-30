# kotlin-minesweeper

## 기능 요구사항
지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

## 추가 기능 분석: 오픈할 셀을 선택한다.
- 지뢰인 셀을 선택하면 게임에 패배한다.
- 인접 지뢰 개수가 1이상인 셀을 선택하면 셀이 오픈되며 인접 지뢰 개수가 보인다.
- 인접 지뢰 개수가 0인 셀을 선택 시 지뢰가 없는 인접한 칸이 모두 열리게 된다.
