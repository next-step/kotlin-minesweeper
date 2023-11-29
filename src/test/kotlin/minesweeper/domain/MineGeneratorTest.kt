package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineGeneratorTest : BehaviorSpec({
    Given("지뢰 개수가 주어지면") {
        val height = Size(10)
        val width = Size(10)
        val count = Size(10)
        When("지뢰 생성기는") {
            val mineGenerator = RandomMineGenerator(height, width)
            val mines = mineGenerator.generate(count)
            Then("해당 개수에 맞는 지뢰들을 생성한다.") {
                mines.mines.size shouldBe count.value
            }
        }
    }
})
