package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
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

    Given("높이와 넓이를 곱한 값보다 큰 개수가 주어지면") {
        val height = Size(5)
        val width = Size(5)
        val count = Size(26)
        When("위치 생성기는") {
            val mineGenerator = RandomPositionGenerator(height, width)
            Then("에러를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    mineGenerator.generate(count)
                }
            }
        }
    }
})
