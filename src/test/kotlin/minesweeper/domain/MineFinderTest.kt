package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineFinderTest : BehaviorSpec({
    Given("지뢰 위치들이 주어질 때") {
        val minePosition = Position(1, 1)
        val minePositions = listOf(minePosition)
        When("MineFinder의 convert 함수를 호출하면") {
            val mineFinder = createMineFinder()
            mineFinder.convert(minePositions)
            Then("주어진 지뢰 위치는 지뢰로 변해있다.") {
                mineFinder.find(minePosition).isMine shouldBe true
            }
        }
    }
})

fun createMineFinder(): CellFinder {
    val initPositions = FixedPositionGenerator(Size(10), Size(10)).generateInit()
    return CellFinder(initPositions)
}
