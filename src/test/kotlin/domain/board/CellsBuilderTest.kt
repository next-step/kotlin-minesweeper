package domain.board

import Positions
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.board.cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCount
import minesweeper.domain.cell.Position

class CellsBuilderTest : DescribeSpec({
    describe("셀들 생성") {
        context("위치가 주어지면(4 * 4, 지뢰위치 (1, 1))") {
            val positions = Positions(row = 4, column = 4, minePositions = setOf(Position(1, 1)))

            val cells = cells {
                positions(positions)
            }

            it("모든 셀의 위치는 주어진 위치 (4 * 4)") {
                cells.map { it.position } shouldBe positions.value
            }

            it("셀 마크는 지뢰(1,1) : Mine / 지뢰 인접 : (0,0), (0,1), (0,2), (1,0), (1, 2), (2, 0), (2, 1), (2, 2) : ONE / 나머지: ZERO") {
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

                cells.filter { it.position == Position(1, 1) }.forEach { it.shouldBeTypeOf<Cell.Mine>() }
                cells.filter { it.position in adjacentPositions }.forEach {
                    it.shouldBeTypeOf<Cell.Clear>()
                    it.mineCount shouldBe MineCount.ONE
                }
                cells.filter { it.position !in adjacentPositions && it.position != Position(1, 1) }
                    .forEach {
                        it.shouldBeTypeOf<Cell.Clear>()
                        it.mineCount shouldBe MineCount.ZERO
                    }
            }
        }
    }
})
