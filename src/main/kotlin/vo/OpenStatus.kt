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
        fun from(mineStatus: MineStatus) =
            when (mineStatus) {
                MineStatus.MINED -> MINED
                MineStatus.EMPTY -> COVERED
            }
    }
}
