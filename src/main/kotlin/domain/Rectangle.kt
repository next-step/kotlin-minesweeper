package domain

data class Rectangle(
    val width: Int,
    val height: Int
) {
    fun getAllPositions(): List<Position> {
        return (Position.POSITION_START..height).flatMap { y ->
            (Position.POSITION_START..width).map { x -> Position.of(x, y) }
        }
    }
}
