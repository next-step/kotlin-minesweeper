package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineTest : BehaviorSpec({
    Given("지뢰는") {
        When("좌표 정보를") {
            val point = Point(0, 1)
            Then("생성 시 프로퍼티로 갖는다.") {
                val mine = Mine(point)

                mine.point.row shouldBe 0
                mine.point.col shouldBe 1
            }
        }
    }
})
