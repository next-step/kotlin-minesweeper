package minesweeper.domain

import org.junit.jupiter.api.Test

class CoordinateTest {
    @Test
    internal fun `2 x 2 좌상단 코너`() {
        assertThat(Coordinate(index = 0, Matrix(2, 2)).sideIndexes)
            .isEqualTo(listOf(1, 2, 3))
    }
}
