package minesweeper.error

object MineSweeperErrorMessage {
    const val HEIGHT_MUST_BE_POSITIVE: String = "높이는 양수여야 합니다."
    const val WIDTH_MUST_BE_POSITIVE: String = "너비는 양수여야 합니다."
    const val NUMBER_OF_MINES_MUST_BE_POSITIVE: String = "지뢰의 개수는 양수여야 합니다."
    const val NUMBER_OF_MINES_MUST_BE_LESS_THAN_CELLS: String = "보드의 셀 개수보다 지뢰의 개수가 작아야 합니다."
    const val OUTSIDE_THE_BOUNDS_OF_THE_BOARD: String = "입력한 위치는 보드의 범위를 벗어납니다."
}
