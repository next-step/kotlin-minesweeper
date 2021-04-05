package minesweeper.domain.position

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun `position 이 (1, 1) 인 경우 주위 position은 (1, 2), (2, 1), (2, 2) 3개이다`() {
        val nearByPositions = Position(Coordinate.of(1), Coordinate.of(1)).nearByPositions()

        assertThat(nearByPositions.size).isEqualTo(3)
        assertThat(nearByPositions).containsAll(
            listOf(
                Position(Coordinate.of(1), Coordinate.of(2)),
                Position(Coordinate.of(2), Coordinate.of(1)),
                Position(Coordinate.of(2), Coordinate.of(2))
            )
        )
    }

    @Test
    fun `position 이 (1, 2) 인 경우 주위 position은 (1, 1), (1, 3), (2, 1), (2, 2), (2, 3) 5개이다`() {
        val nearByPositions = Position(Coordinate.of(1), Coordinate.of(2)).nearByPositions()

        assertThat(nearByPositions.size).isEqualTo(5)
        assertThat(nearByPositions).containsAll(
            listOf(
                Position(Coordinate.of(1), Coordinate.of(1)),
                Position(Coordinate.of(1), Coordinate.of(3)),
                Position(Coordinate.of(2), Coordinate.of(1)),
                Position(Coordinate.of(2), Coordinate.of(2)),
                Position(Coordinate.of(2), Coordinate.of(3))
            )
        )
    }

    @Test
    fun `position 이 (2, 2) 인 경우 주위 position은 (1, 1), (1, 2), (1, 3), (2, 1), (2, 3), (3, 1), (3, 2), (3, 3)  8개이다`() {
        val nearByPositions = Position(Coordinate.of(2), Coordinate.of(2)).nearByPositions()

        assertThat(nearByPositions.size).isEqualTo(8)
        assertThat(nearByPositions).containsAll(
            listOf(
                Position(Coordinate.of(1), Coordinate.of(1)),
                Position(Coordinate.of(1), Coordinate.of(2)),
                Position(Coordinate.of(1), Coordinate.of(3)),
                Position(Coordinate.of(2), Coordinate.of(1)),
                Position(Coordinate.of(2), Coordinate.of(3)),
                Position(Coordinate.of(3), Coordinate.of(1)),
                Position(Coordinate.of(3), Coordinate.of(2)),
                Position(Coordinate.of(3), Coordinate.of(3))
            )
        )
    }
}
