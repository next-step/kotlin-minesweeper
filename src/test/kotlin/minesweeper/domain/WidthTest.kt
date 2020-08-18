package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WidthTest {

    @Test
    fun `너비를 반환한다`() {
        val width = Width(10)
        assertThat(width.value).isEqualTo(10)
    }
}
