package domain

import enum.CellStatus
import inteface.RandomMinePlacementStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineManagerTest {

    private lateinit var mineManager: MineManager
    private lateinit var board: Board
    private val height = 5
    private val width = 5
    private val mineCount = 3

    @BeforeEach
    fun setUp() {
        val mineCounter = AdjacentMineCounter()
        val minePlacementStrategy = RandomMinePlacementStrategy()
        mineManager = MineManager(minePlacementStrategy, mineCounter)
        board = Board(height, width, mineManager)
    }

    @Test
    @DisplayName("지뢰 배치 전략과 카운터가 올바르게 작동하는지 확인한다")
    fun `지뢰 배치 전략과 카운터가 올바르게 작동하는지 확인한다`() {
        val firstMove = Position(0, 0)
        board.placeMines(mineCount, firstMove)

        var actualMineCount = 0
        board.processEachCell { _, cellStatus ->
            if (cellStatus == CellStatus.MINE) {
                actualMineCount++
            }
        }

        assertEquals(mineCount, actualMineCount)
        val testPosition = Position(1, 1)
        val minesAround = mineManager.mineCounter.countMinesAround(board, testPosition)
        assertTrue(minesAround in 0..mineCount)
    }
}
