package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionTest {
    @Test
    fun `같은 위치의 Position 객체는 한번 만들면 재사용한다`() {
        val position = Position.get(1, 1)
        val otherPosition = Position.get(1, 1)

        assertThat(position === otherPosition).isTrue()
    }
}
