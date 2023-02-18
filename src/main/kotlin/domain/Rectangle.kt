package domain

import domain.Position.Companion.POSITION_START

data class Rectangle(
    private val width: Width,
    private val height: Height
) {
    fun toPositions(): List<Position> {
        return (POSITION_START..height.value).flatMap { y ->
            (POSITION_START..width.value).map { Position.of(it, y) }
        }
    }
}
