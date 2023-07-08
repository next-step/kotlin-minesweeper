package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.MineBoard.Companion.generateNewMineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.ONE
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Coordinate
import java.lang.IllegalStateException

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

    context("open") {
        test("종료된 게임에서 open 요청을 할 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ).toCells(),
                isEnd = true,
            )
            val exception = shouldThrowExactly<IllegalStateException> { mineBoard.open(Coordinate(0, 1)) }
            exception shouldHaveMessage "이미 종료된 게임은 진행이 불가능합니다."
        }

        test("모든 cell을 open한 경우 isEnd가 true가 된다.") {
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
            mineBoard.open(Coordinate(0, 0))

            val actual = mineBoard.isEnd
            actual shouldBe true
        }

        test("지뢰를 open한 경우 isEnd가 true가 된다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0, ONE),
                    Cell(0, 1, ONE),
                    Cell(1, 0, ONE),
                    Cell(1, 1, MINE),
                ).toCells(),
            )
            mineBoard.open(Coordinate(1, 1))

            val actual = mineBoard.isEnd
            actual shouldBe true
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
