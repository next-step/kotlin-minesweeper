package tdd.minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import tdd.minesweeper.domain.Location
import tdd.minesweeper.domain.dsl.board

class DefaultShouldOpenLocationFinderTest : BehaviorSpec({
    given("5 x 5 크기, 지뢰 개수 5개의 닫힌 셀만 존재하는 보드에서") {
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
        val sut = DefaultShouldOpenLocationFinder()

        `when`("지뢰를 가진 닫힌 셀 위치로 열어야 할 셀 위치를 찾으면") {
            val location = Location(1, 4)
            val result = sut.findAllShouldOpen(board, location)

            then("해당 위치만 열 수 있다") {
                val expected = setOf(location)

                result shouldContainExactly expected
            }
        }

        `when`("이웃한 8방향 중에 지뢰가 있는 셀 위치로 열어야 할 셀 위치를 찾으면") {
            val location = Location(1, 1)
            val result = sut.findAllShouldOpen(board, location)

            then("해당 위치만 열 수 있다") {
                val expected = setOf(location)

                result shouldContainExactly expected
            }
        }

        `when`("이웃한 8방향 중에 지뢰가 없는 셀 위치로 열어야 할 셀 위치를 찾으면") {
            val location = Location(5, 5)
            val result = sut.findAllShouldOpen(board, location)

            then("인접 위치의 셀을 연쇄적으로 탐색하여 지뢰가 있는 셀을 만나기 전까지의 열 수 있는 모든 셀들을 열 수 있다") {
                val expected =
                    setOf(
                        location,
                        Location(2, 4),
                        Location(2, 5),
                        Location(3, 4),
                        Location(3, 5),
                        Location(4, 4),
                        Location(4, 5),
                        Location(5, 4),
                    )

                result shouldContainExactly expected
            }
        }
    }

    given("5 x 5 크기, 지뢰 개수 5개, 열린 셀 1개의 보드에서") {
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
                openAt(1, 1)
            }
        val sut = DefaultShouldOpenLocationFinder()

        `when`("이미 열린 셀의 위치로 열어야 할 셀 위치를 찾으면") {
            val location = Location(1, 1)
            val result = sut.findAllShouldOpen(board, location)

            then("열 수 있는 셀이 없다") {
                result.shouldBeEmpty()
            }
        }
    }
})
