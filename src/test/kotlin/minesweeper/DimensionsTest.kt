package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DimensionsTest {
    @Test
    fun `모든 위치를 반환한다`() {
        val dimensions = Dimensions(2, 2)

        assertThat(dimensions.allPositions()).containsExactly(
            Position(0, 0),
            Position(1, 0),
            Position(0, 1),
            Position(1, 1),
        )
    }
}