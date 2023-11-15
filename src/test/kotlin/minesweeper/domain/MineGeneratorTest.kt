package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class MineGeneratorTest : BehaviorSpec({

    given("지뢰 개수와 지뢰판(10x10)이 주어지면") {
        val mineCount = 10
        val mineMap = MineMap(Height(10), Width(10))
        When("지뢰를 생성할 때") {
            val result = MineGenerator.generate(mineMap, mineCount)
            Then("지뢰의 개수는 10개이다") {
                result.count() shouldBe mineCount
            }
            Then("지뢰의 y좌표는 10이하이다") {
                result.mines.forEach { it.y shouldBeLessThanOrEqual mineMap.height() }
            }
            Then("지뢰의 x좌표는 10이하이다") {
                result.mines.forEach { it.x shouldBeLessThanOrEqual mineMap.width() }
            }
        }
    }
})
