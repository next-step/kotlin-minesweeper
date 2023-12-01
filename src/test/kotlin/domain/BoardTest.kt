package domain

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
        board = Board(5, 5, mineManager) // Board 객체 생성 시 height, width 추가
    }

    @Test
    @DisplayName("Board는 지정된 위치에 지뢰를 정확히 배치한다")
    fun `지정된 위치에 지뢰를 정확히 배치한다`() {
        val testPosition = Position(2, 3)
        board.initializeBoard(listOf(testPosition)) // 높이와 너비를 다시 전달할 필요 없음

        assertTrue(board.hasMineAt(testPosition))
    }

    @Test
    @DisplayName("Board는 지뢰가 없는 위치를 정확히 식별한다")
    fun `지뢰가 없는 위치를 정확히 식별한다`() {
        val minePosition = Position(1, 1)
        val testPosition = Position(2, 2)
        board.initializeBoard(listOf(minePosition))

        assertFalse(board.hasMineAt(testPosition))
    }

    @Test
    @DisplayName("Board는 모든 지뢰의 수를 정확히 계산한다")
    fun `모든 지뢰의 수를 정확히 계산한다`() {
        val minePositions = listOf(Position(1, 1), Position(2, 2), Position(3, 3))
        board.initializeBoard(minePositions)

        assertEquals(minePositions.size, board.countMines())
    }
}
