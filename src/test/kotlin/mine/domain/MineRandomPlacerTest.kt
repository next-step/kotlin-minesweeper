package mine.domain

import mine.domain.Mine.Companion.MINE_SYMBOL
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MineRandomPlacerTest {
    @Test
    fun `지뢰는 개수 배치`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 10

        val board = placer.placeMines(height, width, mineCount)

        val placedMines = board.sumOf { row -> row.count { it == MINE_SYMBOL } }
        assertEquals(mineCount, placedMines)
    }

    @Test
    fun `지뢰는 중복 위치 배치 테스트`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 10

        val board = placer.placeMines(height, width, mineCount)

        val allCells = board.flatten()
        val distinctMines = allCells.filter { it == MINE_SYMBOL }.size
        assertEquals(mineCount, distinctMines)
    }

    @Test
    fun `빈 보드에 지뢰가 없는 경우`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 0

        val board = placer.placeMines(height, width, mineCount)

        val placedMines = board.sumOf { row -> row.count { it == MINE_SYMBOL } }
        assertEquals(0, placedMines)
    }
}
