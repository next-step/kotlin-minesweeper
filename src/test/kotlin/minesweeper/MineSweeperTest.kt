package minesweeper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperState
import minesweeper.domain.Position
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class MineSweeperTest : FunSpec({
    test("MineCell을 열면 게임 패배") {
            val mineSweeper = MineSweeper(
                mapOf(
                    Position(0, 0) to MineCell(),
                    Position(1, 0) to SafeCell(1),
                )
            )

            mineSweeper.openCell(Position(0, 0)) shouldBe MineSweeperState.LOSE

    }

    test("모든 SafeCell을 열면 승리") {
        val mineSweeper = MineSweeper(
            mapOf(
                Position(0, 0) to MineCell(),
                Position(1, 0) to SafeCell(1),
            )
        )

        mineSweeper.openCell(Position(1, 0)) shouldBe MineSweeperState.WIN
        mineSweeper.getRow(0)[1].isOpened shouldBe true
    }
})
