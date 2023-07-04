package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.MineBoard.Companion.generateNewMineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellType
import minesweeper.domain.cell.Cells

private fun List<Cell>.toCells() = Cells(this)

class MineBoardTest : FunSpec({

    context("init") {
        test("높이가 0이하인 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> {
                MineBoard(
                    0,
                    2,
                    listOf(
                        Cell(0, 0),
                        Cell(0, 1),
                        Cell(1, 0),
                        Cell(1, 1),
                    ).toCells(),
                )
            }
            exception shouldHaveMessage "지뢰찾기맵 높이는 1이상이어야 합니다."
        }

        test("너비가 0이하인 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> {
                MineBoard(
                    2,
                    0,
                    listOf(
                        Cell(0, 0),
                        Cell(0, 1),
                        Cell(1, 0),
                        Cell(1, 1),
                    ).toCells(),
                )
            }
            exception shouldHaveMessage "지뢰찾기맵 너비는 1이상이어야 합니다."
        }
    }

    context("currentBoard") {
        test("현재 맵의 정보를 반환한다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ).toCells(),
            )
            val actual = mineBoard.currentBoard()

            actual.height shouldBe 2
            actual.width shouldBe 2
            actual.values shouldHaveSize 4
        }
    }

    context("generateNewMineBoard") {
        test("새로운 지뢰찾기맵을 생성한다.") {
            val actual = generateNewMineBoard(2, 4)
            actual.cells.values shouldHaveSize 8
        }
    }
})
