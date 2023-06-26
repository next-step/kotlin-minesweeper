package minesweeper.domain

enum class SymbolType {
    BLIND,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    MINE;

    fun isUpdatable() = this !in IMMUTABLE_SYMBOLS

    companion object {
        private val IMMUTABLE_SYMBOLS = listOf(EIGHT, BLIND, MINE)

        fun from(count: Int): SymbolType =
            when (count) {
                0 -> ZERO
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                else -> throw IllegalArgumentException("적절한 타입을 찾을 수 없습니다. Input: $count")
            }
    }
}
