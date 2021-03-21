package minesweeper.domain

import kotlin.math.max

class Matrix(private val width: Int, private val height: Int) {
    private val base = max(width, height)
    fun around(index: Int): List<Int> {
        val (x, y) = toPosition(index)

        return Around.apply(x, y)
            .filter { it.valid(width, height) }
            .map { toIndex(it) }
    }

    private fun toPosition(index: Int): Position {
        return Position(index % width, index / width)
    }

    private fun toIndex(position: Position): Int {
        return position.x + position.y * width
    }
}
