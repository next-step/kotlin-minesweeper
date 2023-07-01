package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class MineBoardTest : FunSpec({

    context("placeMine") {
        test("지뢰 갯수가 0보다 작은 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                listOf(
                    Cell(Row(0), Column(0), CellType.NONE),
                ),
            )

            val exception = shouldThrowExactly<IllegalArgumentException> { mineBoard.placeMine(0) }
            exception shouldHaveMessage "지뢰 갯수는 1이상이어야 합니다."
        }

        test("지뢰 갯수가 cell 갯수보다 큰 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                listOf(
                    Cell(Row(0), Column(0), CellType.NONE),
                    Cell(Row(0), Column(1), CellType.NONE),
                    Cell(Row(1), Column(0), CellType.NONE),
                    Cell(Row(1), Column(1), CellType.NONE),
                ),
            )

            val exception = shouldThrowExactly<IllegalArgumentException> { mineBoard.placeMine(4) }
            exception shouldHaveMessage "지뢰 갯수는 현재 cell크기 4보다 작은 값을 입력하여야 합니다."
        }

        test("이미 지뢰가 배치되어 있는 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                listOf(
                    Cell(Row(0), Column(0), CellType.MINE),
                    Cell(Row(0), Column(1), CellType.NONE),
                    Cell(Row(1), Column(0), CellType.NONE),
                    Cell(Row(1), Column(1), CellType.NONE),
                ),
            )

            val exception = shouldThrowExactly<IllegalStateException> { mineBoard.placeMine(1) }
            exception shouldHaveMessage "이미 지뢰가 배치되어 있습니다."
        }
    }
})
