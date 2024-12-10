package minesweeper.domain

import minesweeper.common.ZERO

data class Point(
    val row: Int,
    val col: Int,
) {
    init {
        require(row >= ZERO && col >= ZERO) { POINT_PROPERTY_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val POINT_PROPERTY_VALUE_EXCEPTION_MESSAGE = "좌표 행, 열 값는 양수여야 합니다."
    }
}
