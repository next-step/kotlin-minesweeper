package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionTest {
    @Test
    fun ` x, y 좌표를 가진다`() {
        val position = Position(1, 2)

        assertAll(
            { assertThat(position.x).isEqualTo(1) },
            { assertThat(position.y).isEqualTo(2) },
        )
    }
}
