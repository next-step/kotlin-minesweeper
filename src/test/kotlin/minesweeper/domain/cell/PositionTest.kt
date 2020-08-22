package minesweeper.domain.cell

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PositionTest {
    @DisplayName(value = "좌표가 같은 Position은 같은 position이다.")
    @Test
    fun checkSamePosition() {
        assertThat(Position(1, 1)).isEqualTo(Position(1, 1))
    }

    @DisplayName(value = "좌표가 다른 Position은 다른 position이다.")
    @Test
    fun checkTheOtherPosition() {
        assertThat(Position(1, 0)).isNotEqualTo(Position(1, 1))
    }
}
