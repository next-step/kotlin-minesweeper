package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class MineGeneratorTest : BehaviorSpec({

    given("지뢰 20개와 지뢰판(10x10)이 주어지면") {
        val mineCount = 20
        val mineSweeperMap = MineSweeperMap(Height(10), Width(10))
        When("지뢰를 생성할 때") {
            val result = MineGenerator.generate(mineSweeperMap.createPosition(), mineCount)
            Then("지뢰의 개수는 20개이다") {
                result.count() shouldBe mineCount
            }
            Then("지뢰의 y좌표는 10이하이다") {
                result.mines.forEach { it.position.y shouldBeLessThanOrEqual 10 }
            }
            Then("지뢰의 x좌표는 10이하이다") {
                result.mines.forEach { it.position.x shouldBeLessThanOrEqual 10 }
            }
        }
    }

    given("지뢰 17개와 지뢰판(4x4)이 주어지면") {
        val mineCount = 17
        val minSweeperMap = MineSweeperMap(Height(4), Width(4))
        When("지뢰를 생성할 때") {
            val exception = shouldThrow<IllegalArgumentException> {
                MineGenerator.generate(minSweeperMap.createPosition(), mineCount)
            }
            then("에러가 발생한다.") {
                exception.message shouldBe "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
            }
        }
    }
})
