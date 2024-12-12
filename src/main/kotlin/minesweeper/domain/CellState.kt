package minesweeper.domain

enum class CellState(private val symbol: String) {
    MINE("*"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    ;

    fun getSymbol(): String {
        return symbol
    }

    companion object {
        fun fromMineCount(count: Int): CellState {
            return when (count) {
                0 -> ZERO
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                else -> throw IllegalArgumentException("Invalid mine count: $count")
            }
        }
    }
}
