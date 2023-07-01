package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.CellType.MINE
import minesweeper.CellType.NONE

class CellTest : FunSpec({

    context("isMine") {
        forAll(
            row(NONE, false),
            row(MINE, true),
        ) { input, expected ->
            test("${input}타입인 cell은 mine이 ${expected}이다.") {
                val actual = Cell(Row(0), Column(0), input).isMine()
                actual shouldBe expected
            }
        }
    }

    context("changeToMine") {
        test("이미 지뢰라면 예외가 발생한다.") {
            val cell = Cell(Row(0), Column(0), MINE)
            val exception = shouldThrowExactly<IllegalStateException> { cell.changeToMine() }
            exception shouldHaveMessage "지뢰는 지뢰로 변경할 수 없습니다."
        }
    }
})
