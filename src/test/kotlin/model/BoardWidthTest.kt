package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardWidthTest {
    @Test
    fun `너비가 0 이하일 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            BoardWidth(0)
        }
        assertThat(exception.message).isEqualTo("너비는 0보다 커야 합니다.")
    }
}
