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
        val minePlacer = ShuffledMinePlacer()
        val mineCounter = AdjacentMineCounter()
        val minePlacementStrategy = RandomMinePlacementStrategy()
        mineManager = MineManager(minePlacementStrategy, minePlacer, mineCounter)
        board = Board(mineManager)
    }

    @Test
    @DisplayName("Board는 지정된 위치에 지뢰를 정확히 배치한다")
    fun `지정된 위치에 지뢰를 정확히 배치한다`() {
        val height = 5
        val width = 5
        val testPosition = Position(2, 3)

        board.initializeBoard(height, width, listOf(testPosition))

        assertTrue(board.hasMineAt(testPosition))
    }

    @Test
    @DisplayName("Board는 지뢰가 없는 위치를 정확히 식별한다")
    fun `지뢰가 없는 위치를 정확히 식별한다`() {
        val height = 5
        val width = 5
        val minePosition = Position(1, 1)
        val testPosition = Position(2, 2)

        board.initializeBoard(height, width, listOf(minePosition))

        assertFalse(board.hasMineAt(testPosition))
    }

    @Test
    @DisplayName("Board는 모든 지뢰의 수를 정확히 계산한다")
    fun `모든 지뢰의 수를 정확히 계산한다`() {
        val height = 5
        val width = 5
        val minePositions = listOf(Position(1, 1), Position(2, 2), Position(3, 3))

        board.initializeBoard(height, width, minePositions)

        assertEquals(minePositions.size, board.countMines())
    }
}
