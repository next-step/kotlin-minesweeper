package domain

import enum.CellStatus
import inteface.RandomMinePlacementStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
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
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)

        val actualMineCount = board.cells.count { it.isMine }
        assertEquals(mineCount, actualMineCount)
    }

    @Test
    @DisplayName("Board는 지뢰가 없는 위치를 정확히 식별한다")
    fun `지뢰가 없는 위치를 정확히 식별한다`() {
        val mineCount = 3
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)

        assertFalse(board.findCell(firstMove)?.isMine ?: true)
    }

    @Test
    @DisplayName("지뢰를 선택하면 게임이 종료된다")
    fun `지뢰를 선택하면 게임이 종료된다`() {
        val mineCount = 1
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)
        val minePosition = board.cells.first { it.isMine }.position

        val result = board.openCell(minePosition)

        assertTrue(result)
    }

    @Test
    @DisplayName("지뢰가 없는 인접한 칸을 열면 해당 칸이 정확하게 열린다")
    fun `지뢰가 없는 인접한 칸을 정확하게 열린다`() {
        val mineCount = 1
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)

        val safePosition = Position(1, 1)
        board.openCell(safePosition)

        assertEquals(CellStatus.OPEN, board.findCell(safePosition)?.status)

        NeighborPositions(safePosition, board.height, board.width).positions.forEach { adjacentPosition ->
            when {
                board.findCell(adjacentPosition)?.isMine == false -> assertEquals(CellStatus.OPEN, board.findCell(adjacentPosition)?.status)
                else -> assertNotEquals(CellStatus.OPEN, board.findCell(adjacentPosition)?.status)
            }
        }
    }

    @Test
    @DisplayName("지뢰를 선택하면 게임이 종료되고 해당 셀의 상태는 MINE으로 유지된다")
    fun `지뢰를 선택하면 게임이 종료되고 해당 셀의 상태는 MINE으로 유지된다`() {
        val mineCount = 1
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)
        val minePosition = board.cells.first { it.isMine }.position

        board.openCell(minePosition)

        assertEquals(CellStatus.MINE, board.findCell(minePosition)?.status)
    }
}
