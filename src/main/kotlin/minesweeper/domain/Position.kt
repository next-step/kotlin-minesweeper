package minesweeper.domain

private val POSITION_REGULAR_EXPRESSION = "^(\\d{1,2},)+\\d{1,2}$".toRegex()

data class Position(val x: XPosition, val y: YPosition) {
    companion object {
        fun of(positionString: String): Position {
            require(POSITION_REGULAR_EXPRESSION.matches(positionString))
            val numbers: List<String> = positionString.split(",")
            return Position(XPosition.of(numbers[0].toInt()), YPosition.of(numbers[1].toInt()))
        }
    }
}
