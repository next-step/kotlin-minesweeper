package minesweeper.domain.tile

enum class Marking {
    MINE,
    EMPTY,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    CLOSED;

    companion object {
        fun toMarking(mineCount: Int): Marking {
            return when (mineCount) {
                0 -> EMPTY
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                else -> throw IllegalArgumentException("지뢰 개수는 0~8개만 가능합니다.")
            }
        }
    }
}
