package domain.game

import Cell
import Mine
import MineBoard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.MineCount
import minesweeper.domain.cell.Position
import minesweeper.domain.game.GameResult
import minesweeper.domain.game.MinesweeperGame

class MinesweeperGameTest : DescribeSpec({
    describe("run()") {
        context("지뢰를 열면") {
            val board = MineBoard(
                Mine(0, 0),
                Cell(0, 1, MineCount.ONE),
                Cell(1, 0, MineCount.ONE),
                Cell(1, 1, MineCount.ONE),
            )
            val positionToOpen = Position(0, 0)
            val game = MinesweeperGame(board) {
                positionToOpen
            }
            game.run()

            it("게임 결과가 LOSS가 된다") {
                game.result shouldBe GameResult.LOSS
            }
        }

        context("주변 지뢰의 수가 0인 셀을 열면") {
            val board = MineBoard(
                Mine(0, 0),
                Cell(0, 1, MineCount.TWO, isOpened = false),
                Mine(0, 2),
                Cell(1, 0, MineCount.ONE, isOpened = false),
                Cell(1, 1, MineCount.TWO, isOpened = false),
                Cell(1, 2, MineCount.ONE, isOpened = false),
                Cell(2, 0, MineCount.ZERO, isOpened = false),
                Cell(2, 1, MineCount.ONE, isOpened = false),
                Cell(2, 2, MineCount.ZERO, isOpened = false),
            )
            val positionToOpen = Position(2, 2)
            val game = MinesweeperGame(board) {
                positionToOpen
            }
            game.run()

            it("인접한 칸이 열린다") {
                game.board shouldBe MineBoard(
                    Mine(0, 0),
                    Cell(0, 1, MineCount.TWO, isOpened = false),
                    Mine(0, 2),
                    Cell(1, 0, MineCount.ONE, isOpened = false),
                    Cell(1, 1, MineCount.TWO, isOpened = true),
                    Cell(1, 2, MineCount.ONE, isOpened = true),
                    Cell(2, 0, MineCount.ZERO, isOpened = false),
                    Cell(2, 1, MineCount.ONE, isOpened = true),
                    Cell(2, 2, MineCount.ZERO, isOpened = true),
                )
            }
        }

        context("모든 셀을 다 열면") {
            val board = MineBoard(
                Mine(0, 0),
                Cell(0, 1, MineCount.TWO, isOpened = false),
                Mine(0, 2),
                Cell(1, 0, MineCount.ONE, isOpened = true),
                Cell(1, 1, MineCount.TWO, isOpened = true),
                Cell(1, 2, MineCount.ONE, isOpened = true),
                Cell(2, 0, MineCount.ZERO, isOpened = true),
                Cell(2, 1, MineCount.ONE, isOpened = true),
                Cell(2, 2, MineCount.ZERO, isOpened = true),
            )
            val positionToOpen = Position(0, 1)
            val game = MinesweeperGame(board) {
                positionToOpen
            }
            game.run()

            it("게임 결과는 WIN이다") {
                game.result shouldBe GameResult.WIN
            }
        }

        context("보드의 범위가 아닌 칸을 열면") {
            val board = MineBoard(
                Mine(0, 0),
                Cell(0, 1, MineCount.ONE),
                Cell(1, 0, MineCount.ONE),
                Cell(1, 1, MineCount.ONE),
            )
            val positionToOpen = Position(2, 2)
            val game = MinesweeperGame(board) {
                positionToOpen
            }

            it("IllegalArgumentException이 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    game.run()
                }
            }
        }

        context("종료된 게임을 실행하면") {
            val board = MineBoard(
                Mine(0, 0),
                Cell(0, 1, MineCount.ONE),
                Cell(1, 0, MineCount.ONE, isOpened = true),
                Cell(1, 1, MineCount.ONE, isOpened = true),
            )
            val positionToOpen = Position(0, 1)
            val game = MinesweeperGame(board) {
                positionToOpen
            }
            game.run()
            game.result.shouldNotBeNull()

            it("IllegalArgumentException이 발생한다") {
                shouldThrow<IllegalStateException> {
                    game.run()
                }
            }
        }
    }
})
