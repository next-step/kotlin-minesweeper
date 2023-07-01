package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CellTest : FunSpec({

    context("지뢰인지 여부에 따라 셀을 반환한다") {
        data class IsMineCell(val isMine: Boolean, val cell: Cell)
        withData(
            IsMineCell(true, Cell.MINE),
            IsMineCell(false, Cell.CLOSED),
        ) { (isMine, cell) ->
            Cell.of(isMine) shouldBe cell
        }
    }

    context("지뢰인지 여부를 반환한다") {
        data class CellIsMine(val cell: Cell, val expected: Boolean)
        withData(
            CellIsMine(Cell.MINE, true),
            CellIsMine(Cell.CLOSED, false),
        ) { (cell, expected) ->
            cell.isMine() shouldBe expected
        }
    }
})
