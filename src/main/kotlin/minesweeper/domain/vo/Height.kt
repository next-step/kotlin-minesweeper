package minesweeper.domain.vo

import minesweeper.extension.isPositive

@JvmInline
value class Height(
    val value: Int
) {
    init {
        require(value.isPositive()) { "높이는 양수만 입력 가능합니다." }
    }
}
