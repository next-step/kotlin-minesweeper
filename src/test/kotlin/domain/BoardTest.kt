package domain

import enum.CellStatus
import inteface.RandomMinePlacementStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BoardTest {

    private lateinit var mineManager: MineManager
    private lateinit var board: Board

    @BeforeEach
    fun setUp() {
        val mineCounter = AdjacentMineCounter()
        val minePlacementStrategy = RandomMinePlacementStrategy()
        mineManager = MineManager(minePlacementStrategy, mineCounter)
        board = Board(5, 5, mineManager)
    }

    @Test
    @DisplayName("Board는 지뢰 개수에 따라 적절히 지뢰를 배치한다")
    fun `지뢰 개수에 따라 적절히 지뢰를 배치한다`() {
        val mineCount = 3
        board.placeMines(mineCount)

        val actualMineCount = board.cells.count { it.isMine() }
        assertEquals(mineCount, actualMineCount)
    }

    @Test
    @DisplayName("Board는 지뢰가 없는 위치를 정확히 식별한다")
    fun `지뢰가 없는 위치를 정확히 식별한다`() {
        val mineCount = 3
        board.placeMines(mineCount)

        val testPosition = Position(2, 2)
        assertFalse(board.hasMineAt(testPosition))
    }

    @Test
    @DisplayName("지뢰를 선택하면 게임이 종료된다")
    fun `지뢰를 선택하면 게임이 종료된다`() {
        val mineCount = 1
        board.placeMines(mineCount)
        val minePosition = board.cells.first { it.isMine() }.position

        val result = board.openCell(minePosition)

        assertTrue(result)
    }

    @Test
    @DisplayName("지뢰가 없는 인접한 칸을 열면 해당 칸이 정확하게 열린다")
    fun `지뢰가 없는 인접한 칸을 정확히 연다`() {
        val mineCount = 1
        board.placeMines(mineCount)

        val safePosition = Position(0, 0)
        board.openCell(safePosition)

        var safeCellStatus: CellStatus? = null
        board.processEachCell { position, cellStatus ->
            if (position == safePosition) {
                safeCellStatus = cellStatus
            }
        }

        assertEquals(CellStatus.OPEN, safeCellStatus)
    }

    @Test
    @DisplayName("지뢰를 선택하면 해당 셀의 상태가 열림으로 변경된다")
    fun `지뢰를 선택하면 해당 셀의 상태가 열림으로 변경된다`() {
        val mineCount = 1
        val minePositions = mineManager.minePlacementStrategy.placeMines(5, 5, mineCount)
        board.placeMines(mineCount)
        val minePosition = minePositions.first()

        board.openCell(minePosition)

        var mineCellStatus: CellStatus? = null
        board.processEachCell { position, cellStatus ->
            if (position == minePosition) {
                mineCellStatus = cellStatus
            }
        }

        assertEquals(CellStatus.OPEN, mineCellStatus)
    }
}
