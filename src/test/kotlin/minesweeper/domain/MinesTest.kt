package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesTest : BehaviorSpec({

    given("지뢰가 2개 주어지면") {
        val mine1 = Mine(1, 1)
        val mine2 = Mine(2, 2)
        When("지뢰를 생성할 때") {
            val mines = Mines(listOf(mine1, mine2))
            Then("지뢰의 개수는 2개이다") {
                mines.count() shouldBe 2
            }
        }
    }
})
