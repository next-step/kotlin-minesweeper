package domain

data class Position(
    val x: PositiveNumber,
    val y: PositiveNumber
) {
    companion object {
        fun of(x: Int, y: Int) = Position(PositiveNumber(x), PositiveNumber(y))
    }
}
