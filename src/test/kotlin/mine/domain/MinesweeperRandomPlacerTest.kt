package mine.domain

import io.kotest.matchers.shouldBe
import mine.enums.MineCell
import org.junit.jupiter.api.Test

class MinesweeperRandomPlacerTest {
    @Test
    fun `지뢰는 개수 배치`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 10

        val board = placer.placeMines(height, width, mineCount)

        val placedMines = board.sumOf { row -> row.mineCells.count { it == MineCell.MINE } }
        mineCount shouldBe placedMines
    }

    @Test
    fun `지뢰는 중복 위치 배치 테스트`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 10

        val board = placer.placeMines(height, width, mineCount)

        val allCells = board.map { it.mineCells }.flatten()
        val distinctMines = allCells.count { it == MineCell.MINE }
        mineCount shouldBe distinctMines
    }

    @Test
    fun `빈 보드에 지뢰가 없는 경우`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 0

        val board = placer.placeMines(height, width, mineCount)

        val placedMines = board.sumOf { row -> row.mineCells.count { it == MineCell.MINE } }
        placedMines shouldBe 0
    }
}
