package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.max

class CoordinateTest {
    @Test
    internal fun `2 x 2 좌상단 코너`() {
        assertThat(Coordinate(0, Matrix(2, 2)).sideIndexes)
            .isEqualTo(listOf(1, 2, 3))
    }

    @Test
    internal fun `2 x 2 우상단 코너`() {
        assertThat(Coordinate(1, Matrix(2, 2)).sideIndexes)
            .isEqualTo(listOf(0, 2, 3))
    }

    class Matrix(private val width: Int, private val height: Int) {
        private val base = max(width, height)
        fun around(index: Int): List<Int> {
            val (x, y) = index.toString(base)
                .let {
                    if (it.length == 1) "0$it" else it
                }
                .map { Character.getNumericValue(it) }.toList()
            return Around.apply(x, y).filter { valid(it) }
                .map { "${it.x}${it.y}" }.map { it.toInt(base) }
        }

        private fun valid(position: Position): Boolean {
            if (position.x < 0 || position.y < 0) {
                return false
            }
            if (position.x > width - 1 || position.y > height - 1) {
                return false
            }
            return true
        }
    }

    enum class Around(private val x: Int, private val y: Int) {
        LEFT_TOP(-1, -1), TOP(0, -1), TOP_RIGHT(1, -1),
        LEFT(-1, 0), RIGHT(1, 0),
        LEFT_BOTTOM(-1, 1), BOTTOM(0, 1), BOTTOM_RIGHT(1, 1);

        companion object {
            fun apply(x: Int, y: Int): List<Position> {
                return values().map { Position(it.x + x, it.y + y) }
            }
        }
    }

    class Position(val x: Int, val y: Int)

    class Coordinate(private val index: Int, private val matrix: Matrix) {
        val sideIndexes: List<Int>
            get() {
                return matrix.around(index).sorted()
            }
    }
}
