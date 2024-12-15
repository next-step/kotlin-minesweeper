package tdd.minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CellsTest {

    @Test
    fun `높이와 너비로 Cells를 만든다`() {
        assertDoesNotThrow { Cells(3, 3, 2) }
    }

    @Test
    fun `Cells이 만들어지면 지뢰가 정상적으로 심어진다`() {
        val cells = Cells(3, 3, 2)
        cells.getActiveMineCount() shouldBe 2
    }
}
