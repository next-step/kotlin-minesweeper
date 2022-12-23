package minesweeper.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ColumnCountTest {

    @DisplayName("너비는 1 이상이어야 합니다")
    @Test
    fun minimumSize() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                ColumnCount(0)
            }
    }
}
