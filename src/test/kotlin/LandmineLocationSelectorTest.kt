import BasicCellGridTextFixture.threeByThreeGrid
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class LandmineLocationSelectorTest : BehaviorSpec({
    given("LandmineLocationSelector 는") {
        val sut = LandmineLocationSelector(FixedShuffleAlgorithm())

        `when`("grid 와 countOfLandmine 을 받아") {
            val grid = threeByThreeGrid
            val countOfLandmine = 3

            val results = sut.selectCandidates(grid, countOfLandmine)

            then("countOfLandmine 만큼 지뢰가 될 셀 리스트를 반환한다(셔플 X)") {
                results shouldContainExactlyInAnyOrder
                    listOf(
                        BasicCell(row = 1, column = 1),
                        BasicCell(row = 1, column = 2),
                        BasicCell(row = 1, column = 3),
                    )
            }
        }
    }
})
