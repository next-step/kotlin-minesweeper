package domain.board

import Cell
import Mine
import MineBoard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCount
import minesweeper.domain.cell.Position

class MineBoardTest : DescribeSpec({
    describe("open()") {
        val board = MineBoard(
            Mine(0, 0),
            Cell(0, 1, MineCount.ONE),
            Cell(0, 2, MineCount.ZERO),
            Cell(1, 0, MineCount.ONE),
            Cell(1, 1, MineCount.ONE),
            Cell(1, 2, MineCount.ZERO),
            Cell(2, 0, MineCount.ZERO),
            Cell(2, 1, MineCount.ZERO),
            Cell(2, 2, MineCount.ZERO),
        )
        context("지뢰가 아닌 셀을 열면") {
            val result = board.open(Position(0, 1))

            it("해당 셀이 열린 채로 반환된다") {
                result shouldBe Cell.Clear(Position(0, 1), MineCount.ONE, isOpened = true)
            }
        }

        context("지뢰가 있는 셀을 열면") {
            it("IllegalStateException이 발생한다") {
                shouldThrow<IllegalStateException> {
                    board.open(Position(0, 0))
                }
            }
        }

        context("보드의 위치 범위가 아닌 셀을 열면") {
            it("IllegalArgumentException이 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    board.open(Position(3, 3))
                }
            }
        }
    }

    describe("isMine()") {
        val board = MineBoard(
            Mine(0, 0),
            Cell(0, 1, MineCount.ONE),
            Cell(1, 0, MineCount.ONE),
            Cell(1, 1, MineCount.ONE),
        )
        context("지뢰일 경우") {
            val position = Position(0, 0)

            it("true가 반환된다") {
                val result = board.isMine(position)

                result shouldBe true
            }
        }

        context("지뢰가 아닐 경우") {
            val position = Position(0, 1)

            it("false가 반환된다") {
                val result = board.isMine(position)

                result shouldBe false
            }
        }

        context("보드 내의 위치가 아닐 경우") {
            val position = Position(0, 2)

            it("IllegalArgumentException이 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    board.isMine(position)
                }
            }
        }
    }
})
