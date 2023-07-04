package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.MineBoard.Companion.generateNewMineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellType

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
                    ),
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
                    ),
                )
            }
            exception shouldHaveMessage "지뢰찾기맵 너비는 1이상이어야 합니다."
        }
    }

    context("placeMine") {
        test("지뢰 갯수가 0보다 작은 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                1,
                1,
                listOf(
                    Cell(0, 0),
                ),
            )

            val exception = shouldThrowExactly<IllegalArgumentException> { mineBoard.placeMine(0) }
            exception shouldHaveMessage "지뢰 갯수는 1이상이어야 합니다."
        }

        test("지뢰 갯수가 cell 갯수보다 큰 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )

            val exception = shouldThrowExactly<IllegalArgumentException> { mineBoard.placeMine(4) }
            exception shouldHaveMessage "지뢰 갯수는 현재 cell크기 4보다 작은 값을 입력하여야 합니다."
        }

        test("이미 지뢰가 배치되어 있는 경우 예외가 발생한다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0, CellType.MINE),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )

            val exception = shouldThrowExactly<IllegalStateException> { mineBoard.placeMine(1) }
            exception shouldHaveMessage "이미 지뢰가 배치되어 있습니다."
        }

        test("지뢰를 배치하고 지뢰 근처의 카운트를 갯수에 맞게 올린다.") {
            val cells = listOf(
                Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2),
                Cell(1, 0),
                Cell(1, 1),
                Cell(1, 2),
                Cell(2, 0),
                Cell(2, 1),
                Cell(2, 2),
            )
            val mineBoard = MineBoard(
                3,
                3,
                cells = cells,
            ) { cells[0] }
            mineBoard.placeMine(1)

            val actual = mineBoard.cells.count { it.isMine() }
            actual shouldBe 1
            mineBoard.cells[0].cellType shouldBe CellType.MINE
            mineBoard.cells[1].cellType shouldBe CellType.ONE
            mineBoard.cells[2].cellType shouldBe CellType.ZERO
            mineBoard.cells[3].cellType shouldBe CellType.ONE
            mineBoard.cells[4].cellType shouldBe CellType.ONE
            mineBoard.cells[5].cellType shouldBe CellType.ZERO
            mineBoard.cells[6].cellType shouldBe CellType.ZERO
            mineBoard.cells[7].cellType shouldBe CellType.ZERO
            mineBoard.cells[8].cellType shouldBe CellType.ZERO
        }

        test("랜덤한 위치에 지뢰를 배치한다.") {
            val mineBoard = MineBoard(
                2,
                2,
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )
            mineBoard.placeMine(2)
            val actual = mineBoard.cells.count { it.isMine() }

            actual shouldBe 2
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
                ),
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
            actual.cells shouldHaveSize 8
        }
    }
})
