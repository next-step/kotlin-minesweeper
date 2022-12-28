package minesweeper.domain

enum class ExceptionReason(val message: String) {
    NEGATIVE_POINT_VALUE("좌표 값은 음수가 될 수 없습니다."),
    MINE_COUNT_OVER_BLOCKS("지뢰의 개수는 총 블록의 개수보다 많을 수 없습니다.")
}
