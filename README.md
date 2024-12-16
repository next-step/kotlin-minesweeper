# kotlin-minesweeper

# 기능 요구사항
- [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다
- [x] 지뢰찾기 보드를 그린다
- [x] 지뢰는 랜덤으로 배치한다
- [x] 블록은 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수를 표시한다.
- [x] 출력시 지뢰는 숫자를 무시하고 지뢰 모양을 지뢰가 아닌칸은 숫자를 출력한다
- [x] 지뢰칸을 열 경우 지뢰가 없는 인접한 칸이 모두 열린다

# 도메인 요구사항

# 지뢰찾기 보드
- [x] 높이와 너비에 행렬형태로 보드마다 칸의 상태를 가진다
- [x] 지뢰 위치 결정시 주변 칸 8칸에 숫자 1을 더한다
- [x] 지뢰를 열면 지뢰 보드에 인접한 칸의 열림상태를 변경한다

# 지뢰찾기 칸
- [x] 지뢰찾기 칸은 지뢰인지 여부를 가진다
- [x] 지뢰 찾기 칸은 주변의 지뢰 수를 가진다
- [x] 열림 여부를 가진다

# 게임
- [x] 지뢰찾기 보드, 지뢰의 갯수를 가진다
- [x] 지뢰의 위치를 결정한다(중복없이)
- [x] 모든 지뢰를 제외한 칸을 열면 승리
- [x] 지뢰 칸을 열면 패배