package mine.domain

import io.kotest.matchers.shouldBe
import mine.enums.MineCell
import org.junit.jupiter.api.Test

class MineRandomPlacerTest {
    @Test
    fun `지뢰를 입력 개수만큼 랜덤하게 배치한다`() {
        val placer = MineRandomPlacer()
        val height = 5
        val width = 5
        val mineCount = 10

        val board = placer.placeMines(height, width, mineCount)

        val placedMines = board.sumOf { row -> row.mineCells.count { it == MineCell.MINE } }
        mineCount shouldBe placedMines
    }
}
