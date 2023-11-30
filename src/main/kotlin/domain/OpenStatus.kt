package domain

enum class OpenStatus(val symbol: String) {
    MINE("+"),
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

        private val properties = values().filter { it != MINE }.associateBy { it.symbol.toInt() }

        fun from(nearMineCount: Int, hasMine: Boolean): OpenStatus {
            return if (hasMine) MINE else properties[nearMineCount] ?: throw IllegalStateException()
        }
    }
}
