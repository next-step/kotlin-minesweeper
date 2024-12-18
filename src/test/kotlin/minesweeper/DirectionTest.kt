package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun `주어진 위치의 이웃 위치들을 알수 있다`() {
        val position = Position(0, 0)
        val expectedNeighbors = listOf(
            Position(-1, -1), Position(-1, 0), Position(-1, 1),
            Position(0, -1), Position(0, 1),
            Position(1, -1), Position(1, 0), Position(1, 1)
        )

        val neighbors = Direction.neighbors(position)

        assertThat(neighbors).containsAll(expectedNeighbors)
    }
}