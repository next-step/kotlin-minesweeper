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

        assertTrue(cell.isMine)
    }

    @Test
    @DisplayName("Cell은 비지뢰 상태를 올바르게 처리한다")
    fun `비지뢰 상태를 올바르게 처리한다`() {
        val cell = Cell(Position(0, 0))

        assertFalse(cell.isMine)
    }

    @Test
    @DisplayName("Cell은 상태를 OPEN으로 올바르게 변경한다")
    fun `상태를 OPEN으로 올바르게 변경한다`() {
        val cell = Cell(Position(0, 0))

        cell.open()

        assertTrue(cell.isOpen)
    }

    @Test
    @DisplayName("Cell은 상태를 CLOSED로 올바르게 변경한다")
    fun `상태를 CLOSED로 올바르게 변경한다`() {
        val cell = Cell(Position(0, 0))
        cell.open()

        cell.close()

        assertFalse(cell.isOpen)
    }

    @Test
    @DisplayName("Cell은 인접한 지뢰가 없을 때 올바르게 처리한다")
    fun `인접한 지뢰가 없을 때 올바르게 처리한다`() {
        val cell = Cell(Position(0, 0))

        assertTrue(cell.isAdjacentMinesZero)
    }
}
