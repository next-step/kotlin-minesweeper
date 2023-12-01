package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NeighborMineCountTest {

    private lateinit var neighborMineCount: NeighborMineCount
    private lateinit var boardSettings: BoardSettings
    private lateinit var board: MutableList<CellList>

    @BeforeEach
    fun setUp() {
        neighborMineCount = NeighborMineCount(0)
        boardSettings = BoardSettings(row = 3, col = 3, mineCount = 1)
        board = (0 until boardSettings.row).map { row ->
            CellList.createEmptyRow(row, boardSettings.col)
        }.toMutableList()
    }

    @Test
    fun `NeighborMineCount 생성시 음수가 들어오면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            NeighborMineCount(-1)
        }
    }

    @Test
    fun `3 * 3 게임에서 (0,0), (0,1), (0,2), (1,0) 에 지뢰를 설치한 후 (1,1) 주위에는 4개의 지뢰가 존재한다`() {
        board[0].cells[0].installMine()
        board[0].cells[1].installMine()
        board[0].cells[2].installMine()
        board[1].cells[0].installMine()

        val result = neighborMineCount.calculateNeighborMineCount(Point(1, 1), boardSettings, board)
        assertEquals(4, result.count)
    }
}
