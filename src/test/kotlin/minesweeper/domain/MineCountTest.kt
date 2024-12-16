package minesweeper.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineCountTest {
    @Test
    @DisplayName("지뢰 개수가 0 보다 크다면 유효한 지뢰 개수")
    fun `valid mine count is accepted`() {
        val mineCount = MineCount(10)
        assertEquals(10, mineCount.getCount())
    }

    @Test
    @DisplayName("지뢰 개수가 0 이라면 예외를 던진다")
    fun `mine count validation`() {
        assertThrows<IllegalArgumentException> {
            MineCount(0)
        }
    }
}