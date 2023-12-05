package domain

import domain.enums.CellType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameBoardTest {

    private lateinit var gameBoard: GameBoard

    @BeforeEach
    fun setUp() {
        val boardSettings = BoardSettings(row = 10, col = 3, mineCount = 0)
        gameBoard = GameBoard.of(boardSettings)
    }

    @Test
    fun `높이, 너비를 입력하면 그 크기만큼 gameBoard 가 만들어 진다`() {
        assertEquals(10, gameBoard.board.size)
        assertEquals(3, gameBoard.board[0].cells.size)
    }

    @Test
    fun `mineCount 의 갯수 만큼 지뢰가 만들어진다`() {
        val boardSettings = BoardSettings(row = 10, col = 3, mineCount = 4)
        val gameBoard = GameBoard.of(boardSettings)

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

    @Test
    fun `선택한 cell 이 정상적으로 open 된다`() {
        gameBoard.openCells(Point(1, 1))
        assertEquals(true, gameBoard.board[1].cells[1].cellInfo.isOpened)
    }

    @Test
    fun `선택한 cell 이 MINE 일 경우 open 되지 않는다`() {
        gameBoard.board[1].cells[1].installMine()

        gameBoard.openCells(Point(1, 1))
        assertEquals(true, gameBoard.board[1].cells[1].cellInfo.isOpened)
    }

    @ParameterizedTest
    @CsvSource("0,0", "0,1", "0,2", "1,0", "1,1", "1,2", "2,0", "2,1", "2,2")
    fun `open 한 cell 주위의 MINE 을 제외한 cell 들이 정상적으로 open 된다`(row: Int, col: Int) {
        gameBoard.openCells(Point(1, 1))
        assertEquals(true, gameBoard.board[row].cells[col].cellInfo.isOpened)
    }

    @Test
    fun `open 한 cell 주위의 MINE 은 open 되지 않는다`() {
        gameBoard.board[0].cells[0].installMine()
        gameBoard.openCells(Point(1, 1))

        assertEquals(false, gameBoard.board[0].cells[0].cellInfo.isOpened)
    }
}
