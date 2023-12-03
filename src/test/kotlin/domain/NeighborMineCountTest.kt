package domain

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
            CellList().createEmptyRow(row, boardSettings.col)
        }.toMutableList()
    }

    @Test
    fun `NeighborMineCount 생성시 음수가 들어오면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            NeighborMineCount(-1)
        }
    }
}
