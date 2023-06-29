package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class MineLocationsTest : BehaviorSpec({
    val height = 3
    val width = 10
    val row = MineLocationRow(List(width / 2) { it * 2 })
    val rowList = List(height) { row }
    val systemUnderTest = MineLocations(rowList, width, height)

    Given("지뢰의 좌표가 주어졌다") {
        val point = Point(0, 0)
        When("주변 지뢰의 개수를 구하면") {
            Then("해당 좌표가 지뢰임이 반환된다") {
                systemUnderTest.getMapElement(point) shouldBe MineMapElement()
            }
        }
    }

    forAll(
        table(
            headers("point", "예상결과"),
            row(Point(1, 0), NumberMapElement(4)),
            row(Point(width - 1, 0), NumberMapElement(2)),
            row(Point(width - 1, 1), NumberMapElement(3)),
            row(Point(1, 1), NumberMapElement(6)),
        ),
    ) { point, expectedResult ->
        Given("지뢰가 아닌 좌표(${point.x},${point.y})가 주어졌다") {
            When("주변 지뢰의 개수를 구하면") {
                Then("해당 좌표 근처의 지뢰 개수가 반환된다") {
                    systemUnderTest.getMapElement(point) shouldBe expectedResult
                }
            }
        }
    }
})
