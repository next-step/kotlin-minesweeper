package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeightTest {

    @Test
    fun `높이를 반환한다`() {
        val height = Height(10)
        assertThat(height.value).isEqualTo(10)
    }
}
