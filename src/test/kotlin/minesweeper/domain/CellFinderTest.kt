package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
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

    Given("위치가 주어질 때") {
        val position = Position(2, 2)
        val cellFinder = CellFinder.init(Size(10), Size(10))
        val minePositions = listOf(Position(1, 2), Position(1, 3))
        cellFinder.convert(minePositions)
        When("CellFinder의 getAroundMinesCount 함수를 호출하면") {
            val result = cellFinder.getAroundMinesCount(position)
            Then("자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수를 반환한다.") {
                result shouldBe 2
            }
        }
    }

    Given("지뢰가 있는지 알고 싶은 위치가 주어질 때") {
        val cellFinder = CellFinder.init(Size(10), Size(10))
        val minePositions = listOf(Position(1, 2), Position(1, 3))
        cellFinder.convert(minePositions)
        When("isMine 함수를 호출하면") {
            Then("지뢰가 있는지 여부를 반환한다.") {
                forAll(
                    row(Position(1, 2), true),
                    row(Position(1, 3), true),
                    row(Position(2, 2), false),
                ) { position, expected ->
                    cellFinder.isMine(position) shouldBe expected
                }
            }
        }
    }
})
