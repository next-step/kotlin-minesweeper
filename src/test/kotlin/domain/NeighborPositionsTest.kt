package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NeighborPositionsTest {

    @Test
    @DisplayName("이웃 위치 계산이 올바르게 수행되는지 검증한다")
    fun `이웃 위치 계산 검증`() {
        val centerPosition = Position(2, 2)
        val boardHeight = 5
        val boardWidth = 5
        val neighborPositions = NeighborPositions(centerPosition, boardHeight, boardWidth)

        val expectedNeighborPositions = setOf(
            Position(1, 1), Position(2, 1), Position(3, 1),
            Position(1, 2), Position(3, 2),
            Position(1, 3), Position(2, 3), Position(3, 3)
        )

        assertEquals(expectedNeighborPositions, neighborPositions.positions.toSet())
    }
}
