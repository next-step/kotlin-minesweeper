package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.max

class CoordinateTest {
    /**
     * x, 2
     * 3, 4
     */
    @Test
    internal fun `2 x 2 좌상단 코너`() {
        assertThat(Coordinate(0, Matrix(2, 2)).sideIndexes)
            .isEqualTo(listOf(1, 2, 3))
    }

    /**
     * 0, x
     * 2, 3
     */
    @Test
    internal fun `2 x 2 우상단 코너`() {
        assertThat(Coordinate(1, Matrix(2, 2)).sideIndexes)
            .isEqualTo(listOf(0, 2, 3))
    }

    /**
     * 0, x, 2
     * 3, 4, 5
     */
    @Test
    fun `3 x 2 상단`() {
        assertThat(Coordinate(1, Matrix(3, 2)).sideIndexes)
            .isEqualTo(listOf(0, 2, 3, 4, 5))
    }

    /**
     * 0, 1, x
     * 3, 4, 5
     */
    @Test
    fun `3 x 2 우상단 코너`() {
        assertThat(Coordinate(2, Matrix(3, 2)).sideIndexes)
            .isEqualTo(listOf(1, 4, 5))
    }

    /**
     * 0, 1
     * 2, x
     * 4, 5
     */
    @Test
    fun `2 x 3 오른쪽`() {
        assertThat(Coordinate(3, Matrix(2, 3)).sideIndexes)
            .isEqualTo(listOf(0, 1, 2, 4, 5))
    }

    /**
     * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, x
     * 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
     */
    @Test
    fun `11 x 2 우상단`() {
        assertThat(Coordinate(10, Matrix(11, 2)).sideIndexes)
            .isEqualTo(listOf(9, 20, 21))
    }

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

    data class Position(val x: Int, val y: Int) {
        fun valid(width: Int, height: Int): Boolean = !negative() && !outside(width, height)

        private fun outside(width: Int, height: Int) = x > width - 1 || y > height - 1

        private fun negative() = x < 0 || y < 0

        override fun toString(): String {
            return "$x, $y"
        }
    }

    class Coordinate(private val index: Int, private val matrix: Matrix) {
        val sideIndexes: List<Int>
            get() {
                return matrix.around(index).sorted()
            }
    }
}
