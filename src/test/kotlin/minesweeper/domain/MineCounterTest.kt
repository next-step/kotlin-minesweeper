package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class MineCounterTest : BehaviorSpec({
    val height = 3
    val width = 10
    val row = MineLocationRow(List(width / 2) { it * 2 })
    val rowList = List(height) { row }
    val mineLocations = MineLocations(rowList)
    val systemUnderTest = MineCounter(height, width, mineLocations)

    Given("지뢰의 좌표가 주어졌다") {
        When("주변 지뢰의 개수를 구하면") {
            Then("해당 좌표가 지뢰임이 반환된다") {
                systemUnderTest.getMapElement(0, 0) shouldBe MapElement.MINE
            }
        }
    }

    forAll(
        table(
            headers("X", "Y", "예상결과"),
            row(1, 0, MapElement.FOUR),
            row(width - 1, 0, MapElement.TWO),
            row(width - 1, 1, MapElement.THREE),
            row(1, 1, MapElement.SIX),
        ),
    ) { x, y, expectedResult ->
        Given("지뢰가 아닌 좌표($x,$y)가 주어졌다") {
            When("주변 지뢰의 개수를 구하면") {
                Then("해당 좌표 근처의 지뢰 개수가 반환된다") {
                    systemUnderTest.getMapElement(x, y) shouldBe expectedResult
                }
            }
        }
    }
})
