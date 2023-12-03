package domain

import domain.enums.CellType
import domain.strategyImpl.RandomPointFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameBoardTest {

    @Test
    fun `높이, 너비를 입력하면 그 크기만큼 gameBoard 가 만들어 진다`() {
        val boardSettings = BoardSettings(row = 10, col = 3, mineCount = 5)
        val gameBoard = GameBoard().from(boardSettings, RandomPointFactory())

        assertEquals(10, gameBoard.board.size)
        assertEquals(3, gameBoard.board[0].cells.size)
    }

    @Test
    fun `mineCount 의 갯수 만큼 지뢰가 만들어진다`() {
        val boardSettings = BoardSettings(row = 10, col = 3, mineCount = 4)
        val gameBoard = GameBoard().from(boardSettings, RandomPointFactory())

        var mineCount = 0
        gameBoard.board.forEach { row ->
            row.cells.forEach { cell ->
                if (cell.cellInfo.cellType == CellType.MINE) {
                    mineCount++
                }
            }
        }
        assertEquals(4, mineCount)
    }
}
