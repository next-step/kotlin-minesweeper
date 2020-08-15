package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `x, y 길이를 넣으면 모든 위치의 좌표를 만든다`() {
        // given
        val positions = Position.createAll(10, 10)

        // then
        assertThat(positions).hasSize(100)
    }
}
