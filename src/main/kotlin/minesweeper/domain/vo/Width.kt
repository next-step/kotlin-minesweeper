package minesweeper.domain.vo

import minesweeper.extension.isPositive

@JvmInline
value class Width(
    val value: Int
) {
    init {
        require(value.isPositive()) { "너비는 음수가 될수 없습니다." }
    }
}
