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

    fun getNextOrCurrent(): SymbolType {
        return when (this) {
            ZERO -> ONE
            ONE -> TWO
            TWO -> THREE
            THREE -> FOUR
            FOUR -> FIVE
            FIVE -> SIX
            SIX -> SEVEN
            SEVEN -> EIGHT
            else -> this
        }
    }
}
