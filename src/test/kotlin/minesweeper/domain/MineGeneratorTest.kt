package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineGeneratorTest : BehaviorSpec({
    Given("지뢰 개수가 주어지면") {
        val count = 10
        When("지뢰 생성기는") {
            val mineGenerator = RandomMineGenerator(10, 10)
            val mines = mineGenerator.generate(count)
            Then("해당 개수에 맞는 지뢰들을 생성한다.") {
                mines.mines.size shouldBe count
            }
        }
    }
})
