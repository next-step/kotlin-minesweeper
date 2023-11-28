package domain.status

enum class OpenStatus(val symbol: String) {
    COVERED("X"),
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
        private val properties = values().associateBy(OpenStatus::symbol)
        fun from(mineStatus: MineStatus, nearMineCount: Int): OpenStatus =
            when (mineStatus) {
                MineStatus.MINED -> MINED
                MineStatus.EMPTY -> from(nearMineCount)
            }

        private fun from(nearMineCount: Int): OpenStatus =
            properties[nearMineCount.toString()] ?: throw IllegalArgumentException("잘못된 지뢰 개수입니다.")
    }
}
