package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.domain.BasicCellGridTextFixture.threeByThreeGrid
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location

class DefaultLandmineLocationSelectorTest : BehaviorSpec({
    given("LandmineLocationSelector 는") {
        val sut = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())

        `when`("board 와 countOfLandmines 를 받아") {
            val grid = threeByThreeGrid
            val board = GameBoard.from(grid)
            val countOfLandmines = CountOfLandmines(3)

            val results = sut.selectCandidates(board, countOfLandmines)

            then("countOfLandmines 만큼 지뢰가 될 Location 리스트를 반환한다(셔플X)") {
                results shouldContainExactlyInAnyOrder
                    listOf(
                        Location(row = 1, column = 1),
                        Location(row = 1, column = 2),
                        Location(row = 1, column = 3),
                    )
            }
        }
    }
})
