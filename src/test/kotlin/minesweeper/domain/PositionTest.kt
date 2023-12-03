package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @Test
    fun `현재 위치의 상대 좌표(상, 하, 좌, 우, 좌상, 우상, 좌하, 우하)를 반환한다(단, 0 이하의 좌표는 제외)`() {
        // given
        val position = Position(1, 1)
        // when
        val aroundPositions = position.aroundPositions()
        // then
        assertThat(aroundPositions).containsExactlyInAnyOrder(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2)
        )
    }
}
