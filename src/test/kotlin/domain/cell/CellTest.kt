package domain.cell

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCount
import minesweeper.domain.cell.Position

class CellTest : DescribeSpec({
    describe("open()") {
        context("닫혀 있는 셀을 열면") {
            val cell = Cell.Clear(
                Position(0, 0),
                MineCount.ZERO,
                isOpened = false
            )

            cell.open()

            it("셀이 열려 있는 상태가 된다") {
                cell.isOpened() shouldBe true
            }
        }

        context("열려 있는 셀을 열면") {
            val cell = Cell.Clear(
                Position(0, 0),
                MineCount.ZERO,
                isOpened = false
            )

            cell.open()

            it("셀이 열려 있는 상태가 된다") {
                cell.isOpened() shouldBe true
            }
        }
    }
})
