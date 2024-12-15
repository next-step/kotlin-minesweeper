package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.BasicCellGridTextFixture.threeByThreeGrid

class LandmineFieldArchitectTest : BehaviorSpec({
    given("LandmineFieldArchitect 는") {
        val fixedLandmineLocationSelector = LandmineLocationSelector(FixedShuffleAlgorithm())
        val sut =
            LandmineFieldArchitect(
                landmineLocationSelector = fixedLandmineLocationSelector,
                vulture = Vulture(),
            )

        `when`("grid 와 countOfLandmine 을 받아") {
            val grid = threeByThreeGrid
            val countOfLandmine = 3

            val result = sut.design(grid, countOfLandmine)

            then("countOfLandmine 만큼 지뢰가 설치된 grid 를 반환한다") {
                val candidates =
                    fixedLandmineLocationSelector.selectCandidates(grid, countOfLandmine)

                candidates.forEach { candidate ->
                    result[candidate.row() - 1][candidate.column() - 1].shouldBeTypeOf<Landmine>()
                }
            }
        }
    }
})
