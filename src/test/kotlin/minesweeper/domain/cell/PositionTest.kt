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

    @DisplayName(value = "주변 position은 중복 되지 않은 8개의 위치다. ")
    @Test
    fun checkAroundPosition() {
        val aroundPosition = Position(1, 0).getAroundPositions().distinct()
        assertThat(aroundPosition.size)
            .isEqualTo(8)
    }
}
