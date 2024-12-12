package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LandTest : BehaviorSpec({
    Given("땅은") {
        When("인자로 좌표, 지뢰들로") {
            val point = Point(1, 0)
            val points = listOf(Point(0, 0), Point(2, 0), Point(1, 1), Point(5, 5))
            val mines =
                Mines(
                    FakeMineGenerator(points),
                    Height(1),
                    Width(1),
                    MineCount(1),
                )
            Then("생성 시 좌표, 주변 지뢰 개수, 공개 여부 정보를 프로퍼티로 갖는다.") {
                val land = Land(mines, point)

                land.point shouldBe point
                land.aroundMineCount shouldBe 3
            }
        }
    }
})
