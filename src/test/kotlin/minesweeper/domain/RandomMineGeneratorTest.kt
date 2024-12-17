package minesweeper.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RandomMineGeneratorTest {
    @Test
    @DisplayName("주어진 지뢰 개수만큼 랜덤하게 지뢰를 생성")
    fun `random mine positions generation`() {
        val totalCells = 100
        val mineCount = 10
        val minePositions = RandomMineGeneratorImpl().generateMinePositions(totalCells, mineCount)

        assertEquals(mineCount, minePositions.size)
        assertTrue(minePositions.all { it in 0 until totalCells })
    }

    @Test
    @DisplayName("가용한 Cell 개수를 초과해서 지뢰를 생성하고자 하는 경우 예외를 던짐")
    fun `more mines than cells throws exception`() {
        assertThrows<IllegalArgumentException> {
            RandomMineGeneratorImpl().generateMinePositions(10, 20)
        }
    }
}