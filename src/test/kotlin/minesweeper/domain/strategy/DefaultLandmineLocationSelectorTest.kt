package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.domain.Cells
import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.Location
import minesweeper.domain.threeByThreeCells

class DefaultLandmineLocationSelectorTest : BehaviorSpec({
    val sut = DefaultLandmineLocationSelector(FixedShuffleAlgorithm())

    given("3x3의 닫힌 셀들과 3개의 지뢰 개수를 받아") {
        val cells = Cells(threeByThreeCells)
        val countOfLandmines = CountOfLandmines(3)

        `when`("지뢰 매설 후보지를 찾으면") {
            val results = sut.selectCandidates(cells, countOfLandmines)

            then("3개의 지뢰를 매설할 후보 지역을 반환한다") {
                results shouldHaveSize countOfLandmines.value

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
