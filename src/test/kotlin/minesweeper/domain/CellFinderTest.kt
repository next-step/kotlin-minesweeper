package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CellFinderTest : BehaviorSpec({
    Given("지뢰 위치들이 주어질 때") {
        val minePosition = Position(1, 1)
        val minePositions = listOf(minePosition)
        When("CellFinder의 convert 함수를 호출하면") {
            val cellFinder = CellFinder.init(Size(10), Size(10))
            cellFinder.convert(minePositions)
            Then("주어진 지뢰 위치는 지뢰로 변해있다.") {
                cellFinder.find(minePosition).isMine shouldBe true
            }
        }
    }
})
