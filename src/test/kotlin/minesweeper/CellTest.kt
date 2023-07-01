package minesweeper

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CellTest : FunSpec({

    context("isMine") {
        forAll(
            row(CellType.NONE, false),
            row(CellType.MINE, true),
        ) { input, expected ->
            test("${input}타입인 cell은 mine이 ${expected}이다.") {
                val actual = Cell(Row(0), Column(0), input).isMine()
                actual shouldBe expected
            }
        }
    }
})
