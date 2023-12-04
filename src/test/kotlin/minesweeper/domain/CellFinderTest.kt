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
                cellFinder.find(minePosition)?.isMine shouldBe true
            }
        }
    }

    Given("셀이 주어질 때") {
        val cell = Cell(Position(2, 2))
        val cellFinder = CellFinder.init(Size(10), Size(10))
        val minePositions = listOf(Position(1, 2), Position(1, 3))
        cellFinder.convert(minePositions)
        When("CellFinder의 getAroundMinesCount 함수를 호출하면") {
            val result = cellFinder.getAroundMinesCount(cell)
            Then("자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수를 반환한다.") {
                result shouldBe 2
            }
        }
    }
})
