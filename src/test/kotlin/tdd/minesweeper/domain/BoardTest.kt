package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.dsl.board

class BoardTest : BehaviorSpec({
    given("영역과 셀 목록을 받아") {
        val area = Area(height = 3, width = 3)
        val cells = closedCellsBy9

        `when`("보드를") {
            val sut = Board(area = area, cells = cells)

            then("생성할 수 있다") {
                sut.cells shouldBe cells
                sut.area shouldBe area
            }
        }
    }

    given("5 x 5 크기, 지뢰 개수 5개의 닫힌 셀만 존재하는 보드에서") {
        val sut =
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

        `when`("닫힌 셀 수를 계산하면") {
            val result = sut.countOfClosed()

            then("결과는 25개다") {
                result shouldBe 25
            }
        }

        `when`("보드 내에 없는 위치의 셀을 열면") {
            val location = Location(0, 0)

            then("예외를 던진다") {
                shouldThrow<IllegalArgumentException> { sut.open(location) }
            }
        }

        `when`("지뢰를 가진 닫힌 셀을 열면") {
            val location = Location(1, 4)
            val result = sut.open(location)

            then("해당 셀만 열린다") {
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
                        openAt(1, 4)
                    }
                result.cells shouldContainExactly expected.cells
            }

            then("열린 지뢰 셀 수는 1개다") {
                result.countOfMineOpened() shouldBe 1
            }
        }

        `when`("이웃한 8방향 중에 지뢰가 있는 셀을 열면") {
            val location = Location(1, 1)
            val result = sut.open(location)

            then("해당 셀만 열린다") {
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
                        openAt(1, 1)
                    }

                result.cells shouldContainExactly expected.cells
            }
        }

        `when`("이웃한 8방향 중에 지뢰가 없는 셀을 열면") {
            val location = Location(5, 5)
            val result = sut.open(location)

            then("이웃한 지뢰가 없는 셀을 모두 연다") {
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

                result.cells shouldContainExactly expected.cells
            }
        }
    }

    given("5 x 5 크기, 지뢰 개수 5개, 열린 셀 1개의 보드에서") {
        val sut =
            board {
                height(5)
                width(5)
                countOfMines(5)
                mineAt(1, 4)
                mineAt(1, 5)
                mineAt(2, 1)
                mineAt(4, 3)
                mineAt(5, 1)
                openAt(1, 1)
            }

        `when`("이미 열린 셀을 다시 열면") {
            val location = Location(1, 1)
            val result = sut.open(location)

            then("보드는 변하지 않는다") {
                result shouldBe sut
            }
        }
    }
})
