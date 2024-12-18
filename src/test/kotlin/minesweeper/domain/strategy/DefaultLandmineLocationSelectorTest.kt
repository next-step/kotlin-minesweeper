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

    given("cells 와 countOfLandmines 를 받아") {
        val cells = Cells(threeByThreeCells)
        val countOfLandmines = CountOfLandmines(3)

        `when`("selectCandidates2() 를 호출하면") {
            val results = sut.selectCandidates(cells, countOfLandmines)

            then("countOfLandmines 개수 만큼 지뢰 후보 Location 리스트를 반환한다") {
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
