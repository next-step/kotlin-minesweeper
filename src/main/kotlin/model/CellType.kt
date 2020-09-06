package model

enum class CellType(val value: String) {
    MINE("*"),
    NONE("C"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    companion object {
        fun nextValue(cellType: CellType): CellType {
            return when (cellType) {
                ZERO -> ONE
                ONE -> TWO
                TWO -> THREE
                THREE -> FOUR
                FOUR -> FIVE
                FIVE -> SIX
                SIX -> SEVEN
                SEVEN -> EIGHT
                else -> cellType
            }
        }
    }
}
