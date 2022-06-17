package minesweeper.domain.vo

import minesweeper.extension.isPositive

@JvmInline
value class Height(
    val value: Int
) {
    init {
        require(value.isPositive()) { "높이는 음수가 될수 없습니다." }
    }
}
