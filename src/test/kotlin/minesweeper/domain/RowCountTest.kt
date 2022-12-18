package minesweeper.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RowCountTest {

    @DisplayName("높이는 1 이상이어야 합니다")
    @Test
    fun minimumSize() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                RowCount(0)
            }
    }
}
