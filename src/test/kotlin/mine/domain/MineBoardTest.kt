package mine.domain

import io.kotest.matchers.shouldBe
import mine.dto.Coordinate
import mine.enums.MineCell
import mine.view.OutputView
import org.junit.jupiter.api.Test

class MineBoardTest {
    @Test
    fun `선택된 셀이 폭탄인지 획인한다`() {
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

        val mineBoard = MineBoard(mineRows)
        mineBoard.isMine(Coordinate(4, 1)) shouldBe true
        mineBoard.isMine(Coordinate(4, 0)) shouldBe false
    }

    @Test
    fun `선택된 셀 인접에 폭탄이 있다면 해당셀만 열린다`() {
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
        val minesweeper =
            Minesweeper(
                height = 5,
                width = 5,
                mineCount = 4,
                mineBoard = MineBoard(mineRows),
            )

        val coordinate = Coordinate(4, 0)

        minesweeper.openCells(coordinate)

        val resultMineBoard = minesweeper.mineBoard
        val closedCellCount =
            resultMineBoard.rows.sumOf { row ->
                row.mineCells.count { cell -> !cell.isOpen }
            }
        closedCellCount shouldBe 24
    }

    @Test
    fun `인접셀이 폭탄이 있는경우 확장되지 않는다`() {
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
        val minesweeper =
            Minesweeper(
                height = 5,
                width = 5,
                mineCount = 4,
                mineBoard = MineBoard(mineRows),
            )

        val coordinate = Coordinate(1, 1)

        minesweeper.openCells(coordinate)

        val resultMineBoard = minesweeper.mineBoard
        OutputView.gameResult(resultMineBoard)

        (targetRange(0, 3)).forEach { row ->
            val (start, end) =
                when {
                    row <= 1 -> 0 to 3
                    row == 2 -> 0 to 2
                    else -> 0 to 1
                }
            (targetRange(start, end)).forEach { col ->
                resultMineBoard.rows[row].mineCells[col].isOpen shouldBe true
            }
        }

        // close Cell
        (targetRange(0, 4)).forEach { row ->
            val (start, end) =
                when {
                    row <= 1 -> 4 to 4
                    row == 2 -> 3 to 4
                    row == 3 -> 2 to 4
                    else -> 0 to 4
                }

            (targetRange(start, end)).forEach { col ->
                resultMineBoard.rows[row].mineCells[col].isOpen shouldBe false
            }
        }
    }

    private fun targetRange(
        start: Int,
        end: Int,
    ) = start..end
}
