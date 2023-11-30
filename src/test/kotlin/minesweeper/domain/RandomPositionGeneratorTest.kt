package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RandomPositionGeneratorTest : BehaviorSpec({
    Given("높이와 넓이, 개수가 주어지면") {
        val height = Size(10)
        val width = Size(10)
        val count = Size(10)
        When("위치 생성기는") {
            val mineGenerator = RandomPositionGenerator(height, width)
            val positions = mineGenerator.generate(count)
            Then("해당 개수에 맞는 위치들을 생성한다.") {
                positions.size shouldBe count.value
            }
        }
    }
})
