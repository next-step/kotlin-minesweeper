package tdd.minesweeper.domain.type

enum class SymbolType(val value: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    MINE(-1),
    BLIND(-1);

    fun isMarkable() = this in MARKABLE_SYMBOLS

    companion object {
        private val MARKABLE_SYMBOLS = listOf(ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN)

        fun from(capacity: Int): SymbolType = values().find { it.value == capacity }
            ?: throw IllegalArgumentException("유효한 인자가 아닙니다.(ex: $ZERO ~ $EIGHT) Input: $capacity")
    }
}
