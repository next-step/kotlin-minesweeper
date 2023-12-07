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

        mineSweeper.tryOpenCell(Position(0, 0)) shouldBe MineSweeperState.LOSE
    }

    test("모든 SafeCell을 열면 승리") {
        val mineSweeper = MineSweeper(
            mapOf(
                Position(0, 0) to MineCell(),
                Position(1, 0) to SafeCell(1),
            )
        )

        mineSweeper.tryOpenCell(Position(1, 0)) shouldBe MineSweeperState.WIN
        mineSweeper.getRow(0)[1].isOpened shouldBe true
    }

    test("주변에 지뢰가 없는 Cell을 열면 모든 인접한 SafeCell이 열림") {
        val mineSweeper = MineSweeper(
            mapOf(
                Position(0, 0) to MineCell(),
                Position(1, 0) to SafeCell(1),
                Position(2, 0) to SafeCell(0),
                Position(0, 1) to SafeCell(1),
                Position(1, 1) to SafeCell(1),
                Position(2, 1) to SafeCell(0),
                Position(0, 2) to SafeCell(1),
                Position(1, 2) to SafeCell(0),
                Position(2, 2) to SafeCell(0),
            )
        )

        mineSweeper.tryOpenCell(Position(2, 2)) shouldBe MineSweeperState.WIN
    }
})
