package domain

import domain.Position.Companion.POSITION_START

data class Rectangle(
    private val width: Width,
    private val height: Height
) {
    fun getWidth(): Int = width.value
    fun getHeight(): Int = height.value

    fun toPositions(): List<Position> {
        return (POSITION_START until height.value).flatMap { y ->
            (POSITION_START until width.value).map { Position.of(it, y) }
        }
    }
}
