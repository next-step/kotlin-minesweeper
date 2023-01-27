package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MineTest {
    @Test
    fun `지뢰 개수가 0 이하일 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Mine(0)
        }
        assertThat(exception.message).isEqualTo("지뢰 개수는 0보다 커야 합니다.")
    }
}
