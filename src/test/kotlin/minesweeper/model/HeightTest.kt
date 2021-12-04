package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeightTest {

    @Test
    fun `Height은 0보다 작으면 항상 0이 된다`() {
        assertThat(Height.valueOf(-1)).isEqualTo(Height.ZERO)
    }
}
