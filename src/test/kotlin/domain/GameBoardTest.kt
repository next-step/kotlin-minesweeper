package domain

import enum.CellStatus
import inteface.RandomMinePlacementStrategy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameBoardTest {

    private val minePlacementStrategy = RandomMinePlacementStrategy()
    private val minePlacer = ShuffledMinePlacer()
    private val mineCounter = AdjacentMineCounter()

    @Test
    @DisplayName("게임 보드는 주어진 높이와 너비를 가진다")
    fun `게임 보드는 주어진 높이와 너비를 가진다`() {
        val height = 10
        val width = 10
        val board = GameBoard(height, width, minePlacementStrategy, minePlacer, mineCounter)

        assertEquals(height, board.height)
        assertEquals(width, board.width)
    }

    @Test
    @DisplayName("게임 보드는 지정된 수의 지뢰를 랜덤하게 배치한다.")
    fun `게임 보드는 지정된 수의 지뢰를 랜덤하게 배치한다`() {
        val height = 10
        val width = 10
        val mineCount = 10
        val board = GameBoard(height, width, minePlacementStrategy, minePlacer, mineCounter)
        board.placeMines(mineCount)

        assertEquals(mineCount, board.countMines())
    }

    @Test
    @DisplayName("forEachCell 메서드는 모든 셀을 순회한다")
    fun `forEachCell 메서드는 모든 셀을 순회한다`() {
        val board = GameBoard(10, 10, minePlacementStrategy, minePlacer, mineCounter)
        board.placeMines(10)
        var cellCount = 0

        board.forEachCell { _, _ -> cellCount++ }

        assertEquals(100, cellCount)
    }

    @Test
    @DisplayName("GameBoard는 지뢰가 배치된 위치를 정확히 식별한다")
    fun `지뢰가 배치된 위치를 정확히 식별한다`() {
        val height = 5
        val width = 5
        val mineCount = 3
        val board = GameBoard(height, width, minePlacementStrategy, minePlacer, mineCounter)
        board.placeMines(mineCount)

        var identifiedMineCount = 0
        board.forEachCell { _, cellStatus ->
            if (cellStatus == CellStatus.MINE) identifiedMineCount++
        }

        assertEquals(mineCount, identifiedMineCount)
    }
}
