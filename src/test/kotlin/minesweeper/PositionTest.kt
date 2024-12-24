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

    @Test
    fun `해당 위치의 key를 반환한다`() {
        val position = Position(1, 2)

        assertThat(position.key()).isEqualTo(position.hashCode())
    }

    @Test
    fun `해당 위치의 이웃 위치를 반환한다`() {
        val position = Position(1, 1)

        assertThat(position.neighbors()).containsExactly(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )
    }
}
