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
            CellList().createEmptyRow(row, boardSettings.col)
        }.toMutableList()
    }

    @Test
    fun `지뢰를 가진 Cell 에서는 지뢰가 반환되어야 한다`() {
        val cellInfo = CellInfo()
        cellInfo.installMine()
        cellInfo.getNeighborMineCount(boardSettings, board, Point(0, 0))
        assertEquals(CellType.MINE, cellInfo.cellType)
    }

    @Test
    fun `지뢰가 아닌 Cell 에서는 주위 지뢰 갯수가 반환되어야 한다`() {
        val cellInfo = CellInfo()
        board[0].cells[0].installMine()
        board[0].cells[1].installMine()
        board[0].cells[2].installMine()
        board[1].cells[0].installMine()

        cellInfo.getNeighborMineCount(boardSettings, board, Point(1, 1))
        assertEquals(CellType.NOT_MINE, cellInfo.cellType)
        assertEquals(4, cellInfo.neighborMineCount.count)
    }

    @Test
    fun `3 * 3 게임에서 (0,0), (0,1), (0,2), (1,0) 에 지뢰를 설치한 후 (1,1) 주위에는 4개의 지뢰가 존재한다`() {
        val cellInfo = CellInfo()
        board[0].cells[0].installMine()
        board[0].cells[1].installMine()
        board[0].cells[2].installMine()
        board[1].cells[0].installMine()

        cellInfo.getNeighborMineCount(boardSettings, board, Point(1, 1))
        assertEquals(4, cellInfo.neighborMineCount.count)
    }
}
