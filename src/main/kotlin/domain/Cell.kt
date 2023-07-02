package domain

enum class Cell(
    val symbol: String,
) {
    CLOSED("C"),
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

    fun isMine(): Boolean = this == MINE

    fun isClosed(): Boolean = this == CLOSED

    companion object {
        private val neighborMineCountRange: IntRange = 0..8

        fun of(isMine: Boolean): Cell {
            return if (isMine) MINE else CLOSED
        }

        fun of(neighborMineCount: Int): Cell {
            return values().find { it.symbol == neighborMineCount.toString() }
                ?: throw IllegalArgumentException("셀의 주변 지뢰 개수는 $neighborMineCountRange 범위 값 이어야 합니다.")
        }
    }
}
