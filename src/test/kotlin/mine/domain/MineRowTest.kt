package mine.domain

import io.kotest.matchers.shouldBe
import mine.enums.MineCell
import org.junit.jupiter.api.Test

class MineRowTest {
    @Test
    fun `지뢰가 아닌 모든 셀이 열렸는지 확인한다`() {
        val mineRows =
            listOf(
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.Number(1),
                        MineCell.Number(1),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.initial(),
                        MineCell.Number(1),
                        MineCell.Number(2),
                        MineCell.MINE,
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.initial(),
                        MineCell.Number(1),
                        MineCell.Number(2),
                        MineCell.MINE,
                        MineCell.Number(2),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.Number(1),
                        MineCell.Number(2),
                        MineCell.MINE,
                        MineCell.Number(2),
                        MineCell.Number(1),
                    ),
                ),
                MineRow(
                    listOf(
                        MineCell.Number(1),
                        MineCell.MINE,
                        MineCell.Number(2),
                        MineCell.Number(1),
                        MineCell.initial(),
                    ),
                ),
            )

        mineRows.forEach { row ->
            row.mineCells.filter { cell -> cell !is MineCell.MINE }.forEach { it.withOpen() }
        }
        val mineBoard = MineBoard(mineRows)
        mineBoard.areAllSafeCellsOpened() shouldBe true
    }
}
