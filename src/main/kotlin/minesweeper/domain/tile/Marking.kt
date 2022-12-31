package minesweeper.domain.tile

sealed class Marking {
    object MINE : Marking()
    object EMPTY : Marking()
    object ONE : Marking()
    object TWO : Marking()
    object THREE : Marking()
    object FOUR : Marking()
    object FIVE : Marking()
    object SIX : Marking()
    object SEVEN : Marking()
    object EIGHT : Marking()
    object CLOSED : Marking()

    companion object {
        fun toMarkingAsInt(mineCount: Int): Marking {
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
