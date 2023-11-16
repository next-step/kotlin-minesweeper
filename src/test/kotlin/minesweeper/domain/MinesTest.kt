package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesTest : BehaviorSpec({

    given("지뢰가 2개 주어지면") {
        val mine1 = Mine(1 to 1)
        val mine2 = Mine(2 to 2)
        When("지뢰를 생성할 때") {
            val mines = Mines(listOf(mine1, mine2))
            Then("지뢰의 개수는 2개이다") {
                mines.count() shouldBe 2
            }
        }
    }

    given("지뢰(1,1)이 주어지고 지뢰찾기 객체가 주어지면") {
        val mines = Mines(listOf(Mine(1 to 1)))
        When("(1,1)이 지뢰인지 확인하면") {
            val result = mines.isMine(Position(1, 1))
            Then("true를 반환한다") {
                result shouldBe true
            }
        }
    }
})
