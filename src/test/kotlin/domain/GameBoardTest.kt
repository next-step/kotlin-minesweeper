package domain

import enum.CellStatus
import inteface.RandomMinePlacementStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameBoardTest {

    private lateinit var mineManager: MineManager
    private lateinit var gameBoard: GameBoard

    @BeforeEach
    fun setUp() {
        val minePlacementStrategy = RandomMinePlacementStrategy()
        val mineCounter = AdjacentMineCounter()
        mineManager = MineManager(minePlacementStrategy, mineCounter)
        gameBoard = GameBoard(mineManager)
    }

    @Test
    @DisplayName("게임 보드는 지정된 수의 지뢰를 랜덤하게 배치한다")
    fun `지정된 수의 지뢰를 랜덤하게 배치한다`() {
        val height = 10
        val width = 10
        val mineCount = 10
        gameBoard.setupBoard(height, width)
        val firstMove = Position(0, 0)
        gameBoard.placeMines(mineCount, firstMove)

        var identifiedMineCount = 0
        gameBoard.processEachCell { _, cellStatus ->
            if (cellStatus == CellStatus.MINE) identifiedMineCount++
        }

        assertEquals(mineCount, identifiedMineCount)
    }

    @Test
    @DisplayName("forEachCell 메서드는 모든 셀을 순회한다")
    fun `forEachCell 메서드는 모든 셀을 순회한다`() {
        val height = 10
        val width = 10
        gameBoard.setupBoard(height, width)
        gameBoard.placeMines(10, Position(0, 0))
        var cellCount = 0

        gameBoard.processEachCell { _, _ -> cellCount++ }

        assertEquals(height * width, cellCount)
    }

    @Test
    @DisplayName("GameBoard는 지뢰가 배치된 위치를 정확히 식별한다")
    fun `지뢰가 배치된 위치를 정확히 식별한다`() {
        val height = 5
        val width = 5
        val mineCount = 3
        gameBoard.setupBoard(height, width)
        gameBoard.placeMines(mineCount, Position(0, 0))

        var identifiedMineCount = 0
        gameBoard.processEachCell { _, cellStatus ->
            if (cellStatus == CellStatus.MINE) identifiedMineCount++
        }

        assertEquals(mineCount, identifiedMineCount)
    }
}
