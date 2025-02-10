package mine.domain

import io.kotest.matchers.shouldBe
import mine.enums.MineCell
import org.junit.jupiter.api.Test

class BoardCalculatorTest {
    @Test
    fun `인접한 폭탄의 갯수를 계산한다`() {
        val tempBoard =
            listOf(
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.MINE,
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.MINE,
                        MineCell.initial(),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.MINE,
                        MineCell.initial(),
                        MineCell.initial(),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.MINE,
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                    ),
                ),
            )

        val boardCalculator = BoardCalculator()
        val calculatedBoard = boardCalculator.calculateBoard(MineBoard(tempBoard))
        calculatedBoard.rows[0].mineCells[3] shouldBe MineCell.Number(1)
        calculatedBoard.rows[0].mineCells[4] shouldBe MineCell.Number(1)

        calculatedBoard.rows[1].mineCells[2] shouldBe MineCell.Number(1)
        calculatedBoard.rows[1].mineCells[3] shouldBe MineCell.Number(2)

        calculatedBoard.rows[2].mineCells[1] shouldBe MineCell.Number(1)
        calculatedBoard.rows[2].mineCells[2] shouldBe MineCell.Number(2)
        calculatedBoard.rows[2].mineCells[4] shouldBe MineCell.Number(2)

        calculatedBoard.rows[3].mineCells[0] shouldBe MineCell.Number(1)
        calculatedBoard.rows[3].mineCells[1] shouldBe MineCell.Number(2)
        calculatedBoard.rows[3].mineCells[3] shouldBe MineCell.Number(2)
        calculatedBoard.rows[3].mineCells[4] shouldBe MineCell.Number(1)

        calculatedBoard.rows[4].mineCells[0] shouldBe MineCell.Number(1)
        calculatedBoard.rows[4].mineCells[2] shouldBe MineCell.Number(2)
        calculatedBoard.rows[4].mineCells[3] shouldBe MineCell.Number(1)
        calculatedBoard.rows[4].mineCells[4] shouldBe MineCell.Number(0)
    }
}
