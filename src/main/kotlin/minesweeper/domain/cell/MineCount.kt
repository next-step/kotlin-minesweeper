package minesweeper.domain.cell

enum class MineCount(val value: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    companion object {
        fun from(mineCount: Int): MineCount =
            entries.firstOrNull { it.value == mineCount }
                ?: throw IllegalArgumentException("주변 지뢰 개수는 0부터 8까지만 가능합니다")
    }
}
