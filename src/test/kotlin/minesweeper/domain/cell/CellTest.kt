package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.NONE

class CellTest : FunSpec({

    context("isMine") {
        forAll(
            row(NONE, false),
            row(MINE, true),
        ) { input, expected ->
            test("${input}타입인 cell은 mine이 ${expected}이다.") {
                val actual = Cell(0, 0, input).isMine()
                actual shouldBe expected
            }
        }
    }

    context("changeToMine") {
        test("이미 지뢰라면 예외가 발생한다.") {
            val cell = Cell(0, 0, MINE)
            val exception = shouldThrowExactly<IllegalStateException> { cell.changeToMine() }
            exception shouldHaveMessage "지뢰는 지뢰로 변경할 수 없습니다."
        }

        test("지뢰로 변경한다.") {
            val cell = Cell(0, 0, NONE)
            cell.changeToMine()
            val actual = cell.cellType

            actual shouldBe MINE
        }
    }

    context("equals") {
        test("동일한 행과 열에 있으면 같은 cell로 취급한다.") {
            val cell1 = Cell(0, 1, NONE)
            val cell2 = Cell(0, 1, MINE)
            cell1 shouldBe cell2
        }
    }
})
