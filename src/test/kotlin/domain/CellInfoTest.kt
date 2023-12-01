package domain

import domain.enums.CellType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CellInfoTest {

    private lateinit var boardSettings: BoardSettings
    private lateinit var board: MutableList<CellList>

    @BeforeEach
    fun setUp() {
        boardSettings = BoardSettings(row = 3, col = 3, mineCount = 1)
        board = (0 until boardSettings.row).map { row ->
            CellList.createEmptyRow(row, boardSettings.col)
        }.toMutableList()
    }

    @Test
    fun `지뢰를 가진 Cell 에서는 지뢰가 반환되어야 한다`() {
        val cellInfo = CellInfo(CellType.MINE)
        val result = cellInfo.createCellInfo(boardSettings, board, Point(0, 0))
        assertEquals(CellType.MINE, result.cellType)
    }

    @Test
    fun `지뢰가 아닌 Cell 에서는 주위 지뢰 갯수가 반환되어야 한다`() {
        val cellInfo = CellInfo(CellType.NOT_MINE)
        board[0].cells[0].installMine()
        board[0].cells[1].installMine()
        board[0].cells[2].installMine()
        board[1].cells[0].installMine()

        val result = cellInfo.createCellInfo(boardSettings, board, Point(1, 1))
        assertEquals(CellType.NOT_MINE, result.cellType)
        assertEquals(4, result.neighborMineCount.count)
    }
}
