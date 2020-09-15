package minesweeper.domain.square

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PositionTest {
    private lateinit var zeroBoundaryPosition: Position
    private lateinit var maxBoundaryPosition: Position
    private lateinit var insidePosition: Position

    @BeforeEach
    fun setUp() {
        zeroBoundaryPosition = Position(0, 0)
        maxBoundaryPosition = Position(4, 4)
        insidePosition = Position(1, 1)
    }

    @Test
    fun `위치가 경계선 위치인지 확인`() {
        // given
        val height = 3
        val width = 3

        // when
        val isZeroBoundary: Boolean = zeroBoundaryPosition.isBoundary(height, width)
        val isMaxBoundary: Boolean = maxBoundaryPosition.isBoundary(height, width)
        val isAlsoBoundary: Boolean = insidePosition.isBoundary(height, width)

        // then
        assertThat(isZeroBoundary).isTrue()
        assertThat(isMaxBoundary).isTrue()
        assertThat(isAlsoBoundary).isFalse()
    }

    @Test
    fun `방향이 올바르게 더해지는지 확인 (현재위치 1,1)`() {
        // given
        val downRight = Direction.DOWN_RIGHT

        // when
        val nextPosition: Position = insidePosition + downRight

        // then
        assertThat(nextPosition.x).isEqualTo(2)
        assertThat(nextPosition.y).isEqualTo(2)
    }
}
