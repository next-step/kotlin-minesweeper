package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeightTest {

    @Test
    fun `Height은 0보다 작을 수 없다`() {
        assertThat(Height.valueOf(-1)).isEqualTo(Height.ZERO)
    }
}
