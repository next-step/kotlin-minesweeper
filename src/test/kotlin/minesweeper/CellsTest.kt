package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Coordinate

class CellsTest : DescribeSpec({

    describe("isAllBlockOpened") {
        context("Cell 에 Block 이 존재하는 경우") {
            it("모두 열려있다면 true 를 리턴한다.") {
                val cells = Cells(listOf(OpenedCell(Coordinate(0, 0)), OpenedCell(Coordinate(0, 1))))

                cells.isAllBlockOpened() shouldBe true
            }
            it("모두 열려있지 않으면 false 를 리턴한다.") {
                val cells = Cells(listOf(OpenedCell(Coordinate(0, 0)), Cell.Block(Coordinate(0, 1))))

                cells.isAllBlockOpened() shouldBe false
            }
        }
    }

    describe("openAllMine") {
        context("보드에 존재하는 모든 지뢰를") {
            it("open 상태로 바꾼다.") {
                val cells = Cells(listOf(Cell.Mine(Coordinate(0, 0)), Cell.Mine(Coordinate(0, 1))))

                cells.openAllMine()

                cells[0].isOpened() shouldBe true
                cells[1].isOpened() shouldBe true
            }
        }
    }

    describe("findCell") {
        context("Cells 에 Cell 이 존재하는 경우") {
            it("요청한 위치의 Cell 을 리턴한다.") {
                val cells = Cells(listOf(Cell.Block(Coordinate(0, 0)), Cell.Block(Coordinate(0, 1))))

                cells.findCellOrNull(Coordinate(0, 0)) shouldNotBe null
            }
        }
    }
})
