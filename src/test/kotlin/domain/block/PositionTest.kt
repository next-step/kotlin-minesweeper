package domain.block

import domain.block.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionTest {
    @Test
    fun `한 번 만든 좌표는 캐싱된다`() {
        // given
        val position = Position.of(0, 0)
        val retrievePosition = Position.of(0, 0)

        // then
        assertAll(
            { assertThat(position === retrievePosition).isTrue() },
            { assertThat(position == retrievePosition).isTrue() }
        )
    }

    @Test
    fun `특정 좌표의 주위좌표를 가져올 수 있다`() {
        // given
        val position = Position.of(0, 0)
        val expectedPositions = listOf(
            Position.of(-1, -1),
            Position.of(0, -1),
            Position.of(1, -1),
            Position.of(-1, 0),
            Position.of(1, 0),
            Position.of(-1, 1),
            Position.of(0, 1),
            Position.of(1, 1)
        )

        // when
        val surroundingPositions = position.surroundings()

        // then
        assertAll(
            { assertThat(surroundingPositions).containsAll(expectedPositions) },
            { assertThat(surroundingPositions).hasSize(8) }
        )
    }
}
