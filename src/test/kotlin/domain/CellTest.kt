package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CellTest {

    @Test
    @DisplayName("Cell은 지뢰를 올바르게 배치하고 확인한다")
    fun `지뢰를 올바르게 배치하고 확인한다`() {
        val cell = Cell(Position(0, 0))

        cell.placeMine()

        assertTrue(cell.isMine())
    }

    @Test
    @DisplayName("Cell은 비지뢰 상태를 올바르게 처리한다")
    fun `비지뢰 상태를 올바르게 처리한다`() {
        val cell = Cell(Position(0, 0))

        assertFalse(cell.isMine())
    }
}
