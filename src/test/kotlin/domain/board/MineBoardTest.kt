package domain.board

import Positions
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position

class MineBoardTest : DescribeSpec({
    describe("MineBoard 생성") {
        context("위치가 주어지면(4 * 4, 지뢰위치 (1,1))") {
            val positions = Positions(row = 4, column = 4, minePositions = setOf(Position(1, 1)))
            val mineBoard = MineBoard.from(positions)

            it("보드의 위치는 주어진 위치 (4 * 4)") {
                mineBoard.cells.map { it.position } shouldBe positions.value
            }

            it("셀 마크는 지뢰(1,1) : Mine / 지뢰 인접 : (0,0), (0,1), (0,2), (1,0), (1, 2), (2, 0), (2, 1), (2, 2) : ONE / 나머지: ZERO") {
                val cells = mineBoard.cells
                val adjacentPositions = setOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(0, 2),
                    Position(1, 0),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1),
                    Position(2, 2),
                )

                cells.filter { it.position == Position(1, 1) }.forEach { it.mark shouldBe CellMark.MINE }
                cells.filter { it.position in adjacentPositions }.forEach { it.mark shouldBe CellMark.ONE }
                cells.filter { it.position !in adjacentPositions && it.position != Position(1, 1) }
                    .forEach { it.mark shouldBe CellMark.ZERO }
            }
        }
    }
})
