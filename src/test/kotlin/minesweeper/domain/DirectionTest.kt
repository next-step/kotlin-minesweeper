package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun has_8_direction() {
        assertThat(Direction.values()).hasSize(8)
    }
}
