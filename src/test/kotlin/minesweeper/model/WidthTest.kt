package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WidthTest {

    @Test
    fun `Width는 0보다 작으면 항상 0이 된다`() {
        assertThat(Width.valueOf(-1)).isEqualTo(Width.ZERO)
    }
}
