package domain.cell

import Cell
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.CellMark

class CellTest : DescribeSpec({
    describe("open()") {
        context("닫혀 있는 셀을 열면") {
            val mark = CellMark.ZERO
            val cell = Cell(mark = mark)

            val result = cell.open()

            it("셀이 열려 있는 상태가 된다") {
                cell.isOpened shouldBe true
            }

            it("표식이 반환된다") {
                result shouldBe mark
            }
        }

        context("지뢰인 셀을 열면") {
            val mark = CellMark.MINE
            val cell = Cell(mark = mark)

            val result = cell.open()

            it("셀이 열려 있는 상태가 된다") {
                cell.isOpened shouldBe true
            }

            it("지뢰가 반환된다") {
                result shouldBe mark
            }
        }

        context("이미 열려 있는 셀을 열면") {
            val mark = CellMark.ONE
            val cell = Cell(mark = mark, isOpened = true)
            cell.isOpened shouldBe true

            val result = cell.open()

            it("셀은 열려 있는 상태로 있는다") {
                cell.isOpened shouldBe true
            }

            it("표식이 반환다") {
                result shouldBe mark
            }
        }
    }
})
