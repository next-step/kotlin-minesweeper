package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.dsl.board

class GameTest : BehaviorSpec({

    given("높이 5, 너비 5, 지뢰 개수 5를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5

        `when`("게임을 만들면") {
            val sut = Game.from(height = height, width = width, countOfMines = countOfMines)

            then("5 x 5의 영역, 25개의 닫힌 셀과 그 중 5개가 지뢰를 갖고 있는 보드를 갖는다") {
                sut.board.area shouldBe Area(height = height, width = width)
                sut.board.cells.size shouldBe 25
                sut.board.cells.all { cell -> !cell.isOpen() } shouldBe true
                sut.board.cells.filter { it.hasMine() }.size shouldBe 5
            }
        }
    }

    given("5 x 5 사이즈의 지뢰를 5개 가지고 있는 보드를 가졌을 때") {
        val board =
            board {
                height(5)
                width(5)
                countOfMines(5)
                mineAt(1, 4)
                mineAt(1, 5)
                mineAt(2, 1)
                mineAt(4, 3)
                mineAt(5, 1)
            }

        val sut = Game(countOfMines = 5, board = board)
        val location = Location(5, 5)

        `when`("게임은 위치를 받아") {
            sut.open(location)

            then("보드의 셀을 오픈할 수 있다") {
                val expected =
                    board {
                        height(5)
                        width(5)
                        countOfMines(5)
                        mineAt(1, 4)
                        mineAt(1, 5)
                        mineAt(2, 1)
                        mineAt(4, 3)
                        mineAt(5, 1)
                        openAt(2, 4)
                        openAt(2, 5)
                        openAt(3, 4)
                        openAt(3, 5)
                        openAt(4, 4)
                        openAt(4, 5)
                        openAt(5, 4)
                        openAt(5, 5)
                    }

                sut.board.cells shouldContainExactly expected.cells
            }
        }
    }

    given("보드의 닫힌 셀 수와 보드의 총 지뢰 수가 같으면") {
        val board =
            board {
                height(2)
                width(2)
                countOfMines(3)
                mineAt(1, 1)
                mineAt(1, 2)
                mineAt(2, 1)
                openAt(2, 2)
            }
        val sut = Game(countOfMines = 3, board = board)

        `when`("게임은") {
            val result: GameState = sut.state()

            then("승리한 것이다") {
                result shouldBe GameState.WIN
            }
        }
    }

    given("보드에 열린 지뢰 셀이 하나 이상 존재하면") {
        val board =
            board {
                height(2)
                width(2)
                countOfMines(3)
                mineAt(1, 1)
                mineAt(1, 2)
                mineAt(2, 1)
                openAt(1, 1)
            }
        val sut = Game(countOfMines = 3, board = board)

        `when`("게임은") {
            val result: GameState = sut.state()

            then("패배한 것이다") {
                result shouldBe GameState.LOSE
            }
        }
    }

    given("보드에 닫힌 셀의 수가 총 지뢰 수보다 많고 열린 지뢰 셀이 없으면") {
        val board =
            board {
                height(2)
                width(2)
                countOfMines(3)
                mineAt(1, 1)
                mineAt(1, 2)
                mineAt(2, 1)
            }
        val sut = Game(countOfMines = 3, board = board)

        `when`("게임은") {
            val result: GameState = sut.state()

            then("계속할 수 있다") {
                result shouldBe GameState.CONTINUE
            }
        }
    }
})
