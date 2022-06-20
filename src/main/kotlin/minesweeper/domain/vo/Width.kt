package minesweeper.domain.vo

import minesweeper.extension.isPositive

@JvmInline
value class Width(
    val value: Int
) {
    init {
        require(value.isPositive()) { "너비는 양수만 입력 가능합니다." }
    }
}
