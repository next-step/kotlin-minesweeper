package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineGeneratorTest : BehaviorSpec({

    given("높이 5, 너비 5, 지뢰갯수 10개가 주어지면") {
        val height = Height(5)
        val width = Width(5)
        val mineCount = 10
        When("지뢰판을 생성하면") {
            val result = MineGenerator.generate(height, width, mineCount)
            Then("지뢰판의 크기는 25이다.") {
                result.size shouldBe 25
            }
            Then("지뢰의 개수는 10개이다.") {
                result.count { it.isMine() } shouldBe 10
            }
        }
    }

    given("지뢰 26개와 지뢰판(5x5)이 주어지면") {
        val height = Height(5)
        val width = Width(5)
        val mineCount = 26
        When("지뢰를 생성할 때") {
            val exception = shouldThrow<IllegalArgumentException> {
                MineGenerator.generate(height, width, mineCount)
            }
            then("에러가 발생한다.") {
                exception.message shouldBe "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
            }
        }
    }
})
