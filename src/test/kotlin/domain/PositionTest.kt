package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionTest {
    @Test
    internal fun `of()와 생성자는 같은 역할을 한다`() {
        val x = 3
        val y = 5

        val actual = Position.of(3, 5)
        val expected = Position(PositiveNumber(3), PositiveNumber(5))

        assertThat(actual).isEqualTo(expected)
    }
}
