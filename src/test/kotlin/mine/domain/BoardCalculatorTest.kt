package mine.domain

import io.kotest.matchers.shouldBe
import mine.domain.MineRandomPlacer.Companion.DEFAULT_MINE_NUMBER
import mine.enums.MineCell
import org.junit.jupiter.api.Test

class BoardCalculatorTest {
    @Test
    fun `주변 지뢰갯수  확인후 셀 값 변경`() {
        val board =
            listOf(
                MineRow(
                    listOf(
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                        MineCell.MINE,
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                        MineCell.Number(DEFAULT_MINE_NUMBER),
                    ),
                ),
            )
        val result = BoardCalculator().calculateBoard(board)
        val expectedResult =
            listOf(
                MineRow(listOf(MineCell.Number(1), MineCell.Number(1), MineCell.Number(1))),
                MineRow(listOf(MineCell.Number(1), MineCell.MINE, MineCell.Number(1))),
                MineRow(listOf(MineCell.Number(1), MineCell.Number(1), MineCell.Number(1))),
            )

        result shouldBe expectedResult
    }
}
