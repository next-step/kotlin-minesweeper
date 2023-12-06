package minesweeper.domain.cell

enum class CellMark {
    MINE, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, EMPTY;

    companion object {
        fun from(mineCount: Int) = when (mineCount) {
            0 -> ZERO
            1 -> ONE
            2 -> TWO
            3 -> THREE
            4 -> FOUR
            5 -> FIVE
            6 -> SIX
            7 -> SEVEN
            8 -> EIGHT
            else -> throw IllegalArgumentException("주변 지뢰 개수는 0부터 8까지만 가능합니다")
        }
    }
}
