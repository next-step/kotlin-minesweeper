package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({

    context("placeMine") {
        test("이미 지뢰가 배치되어 있는 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                listOf(
                    Cell(Row(0), Column(0), CellType.MINE),
                ),
            )

            val exception = shouldThrowExactly<IllegalStateException> { mineBoard.placeMine() }
            exception.message shouldBe "이미 지뢰가 배치되어 있습니다."
        }
    }
})
