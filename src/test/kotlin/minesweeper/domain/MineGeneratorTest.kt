package minesweeper.domain

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
})
