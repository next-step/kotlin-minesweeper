package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
}
