package domain

import io.kotest.assertions.throwables.shouldThrow
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

    context("닫혀있는지 여부를 반환한다") {
        data class CellIsClosed(val cell: Cell, val expected: Boolean)
        withData(
            CellIsClosed(Cell.MINE, false),
            CellIsClosed(Cell.CLOSED, true),
        ) { (cell, expected) ->
            cell.isClosed() shouldBe expected
        }
    }

    context("주변 지뢰 개수에 따라 셀을 반환한다") {
        data class NeighborMineCountCell(val neighborMineCount: Int, val cell: Cell)
        withData(
            NeighborMineCountCell(0, Cell.ZERO),
            NeighborMineCountCell(1, Cell.ONE),
            NeighborMineCountCell(2, Cell.TWO),
            NeighborMineCountCell(3, Cell.THREE),
            NeighborMineCountCell(4, Cell.FOUR),
            NeighborMineCountCell(5, Cell.FIVE),
            NeighborMineCountCell(6, Cell.SIX),
            NeighborMineCountCell(7, Cell.SEVEN),
            NeighborMineCountCell(8, Cell.EIGHT),
        ) { (neighborMineCount, cell) ->
            Cell.of(neighborMineCount) shouldBe cell
        }
    }

    context("주변 지뢰 개수가 0~8 범위가 아니면 예외가 발생한다.") {
        withData(9, -1) {
            shouldThrow<IllegalArgumentException> {
                Cell.of(it)
            }
        }
    }
})
