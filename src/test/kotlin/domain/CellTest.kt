package domain

import fixture.cell
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CellTest : FunSpec({

    test("셀을 연다") {
        val cell = cell(state = Closed)
        cell.open(3)
        cell shouldBe cell(state = Open(3))
    }

    context("셀이 닫혀 있는지 여부를 반환한다") {
        data class CellStateIsClosed(val cellState: CellState, val expected: Boolean)
        withData(
            CellStateIsClosed(Closed, true),
            CellStateIsClosed(Open(1), false),
        ) { (cellState, expected) ->
            cell(state = cellState).isClosed() shouldBe expected
        }
    }

    test("열린 셀의 주변 지뢰 개수를 반환한다") {
        cell(state = Open(2)).aroundMineCount() shouldBe 2
    }

    test("닫힌 셀의 주변 지뢰 개수를 반환하면 예외가 발생한다") {
        shouldThrow<IllegalStateException> { cell(state = Closed).aroundMineCount() }
    }
})
