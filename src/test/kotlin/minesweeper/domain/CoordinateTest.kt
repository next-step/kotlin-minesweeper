package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

    class Matrix(width: Int, height: Int)

    class Coordinate(index: Int, matrix: Matrix) {
        val sideIndexes: List<Int> = listOf(1, 2, 3)
    }
}
