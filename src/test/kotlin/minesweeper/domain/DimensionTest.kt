package minesweeper.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DimensionTest {
    @Test
    @DisplayName("높이와 너비가 valid 하다면 예외를 던지지 않고 올바른 Dimension 을 계산함")
    fun `valid dimensions are accepted`() {
        val dimension = Dimension(height = 10, width = 10)
        assertEquals(100, dimension.totalCells())
    }

    @Test
    @DisplayName("높이가 0 이면 예외를 던진다")
    fun `height validation`() {
        assertThrows<IllegalArgumentException> {
            Dimension(height = 0, width = 10)
        }
    }

    @Test
    @DisplayName("너비가 0 이면 예외를 던진다")
    fun `width validation`() {
        assertThrows<IllegalArgumentException> {
            Dimension(height = 10, width = 0)
        }
    }
}
