package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.domain.mine.MineType
import minesweeper.dto.HeightResult
import minesweeper.dto.NumberOfMinesResult
import minesweeper.dto.WidthResult
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MinefieldTest {

    @Test
    @DisplayName("지뢰를 10개 등록할 경우 지뢰밭에 지뢰의 수는 10개")
    fun `If you register 10 mines, the number of mines on the minefield is 10`() {
        val widthResult = WidthResult("10")
        val heightResult = HeightResult("10")
        val minefield = Minefield.of(widthResult, heightResult)

        val numberOfMinesResult = NumberOfMinesResult("10")
        val enrolledBoard = minefield.enrollMines(numberOfMinesResult)
        val mines = enrolledBoard.mines.filter { it.mineType == MineType.MINE }

        mines.size shouldBe 10
    }
}
