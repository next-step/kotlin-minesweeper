package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

fun Mine(value: Pair<Int, Int>): Mine {
    return Mine(Position(value.first, value.second))
}

class MineTest : BehaviorSpec({

    given("x좌표가 1이고 y좌표가 1인 지뢰가 있을 때") {
        val position = Position(1, 1)
        When("지뢰를 생성할 때") {
            val mine = Mine(position)
            Then("x좌표는 1이다") {
                mine.x shouldBe position.x
            }
            Then("y좌표는 1이다") {
                mine.y shouldBe position.y
            }
        }
    }
})
