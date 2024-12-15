package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.BasicCellGridTextFixture.threeByThreeGrid

class DefaultLandmineFieldArchitectTest : BehaviorSpec({
    given("LandmineFieldArchitect 는 ") {
        val fixedLandmineLocationSelector = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())
        val sut =
            DefaultLandmineFieldArchitect(
                landmineLocationSelector = fixedLandmineLocationSelector,
                landminePlanter = Vulture(),
            )

        val grid = threeByThreeGrid
        val gameBoard = GameBoard.from(grid)

        `when`("GameBoard와 countOfLandmines 를 받아") {
            val countOfLandmines = CountOfLandmines(3)
            val result = sut.design(gameBoard, countOfLandmines)

            then("countOfLandmines 만큼 지뢰가 설치된 GameBoard를 반환한다") {
                val candidates =
                    fixedLandmineLocationSelector.selectCandidates(gameBoard, countOfLandmines)

                candidates.forEach { candidate ->
                    result.find(candidate).shouldBeTypeOf<Landmine>()
                }
            }
        }
        `when`("GameBoard의 전체 사이즈보다 countOfLandmines의 개수가 더 많으면") {
            val countOfLandmines = CountOfLandmines(10)

            then("IllegalArgumentException 예외를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    sut.design(gameBoard, countOfLandmines)
                }
            }
        }
    }
})
