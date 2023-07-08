package minesweeper.domain.cell

import java.lang.IllegalArgumentException

enum class CellType {
    UNKNOWN,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    MINE,
    ;

    companion object {
        fun Int.toCellType(): CellType =
            when (this) {
                0 -> ZERO
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                else -> throw IllegalArgumentException("지원하지 않는 셀 타입입니다.")
            }
    }
}
