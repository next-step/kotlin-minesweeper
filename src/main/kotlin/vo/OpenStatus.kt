package vo

enum class OpenStatus(val symbol: String) {
    COVERED("@"),
    MINED("+"),
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
        fun from(mineStatus: MineStatus, nearMineCount: Int) =
            when (mineStatus) {
                MineStatus.MINED -> MINED
                MineStatus.EMPTY -> from(nearMineCount)
            }

        private fun from(nearMineCount: Int) =
            when (nearMineCount) {
                0 -> ZERO
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                else -> throw IllegalArgumentException("잘못된 지뢰 개수입니다.")
            }
    }
}
