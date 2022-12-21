package domain.strategy

import domain.Coordinate
import domain.Height
import domain.Mine
import domain.MineCnt
import domain.Width
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class RandomMineBoardGenerateStrategyTest : BehaviorSpec({
    Given("보드를 ") {
        val height = Height(5)
        val width = Width(9)
        val mineCnt = MineCnt(10)

        When("생성하면 ") {
            val fields = RandomMineBoardGenerateStrategy().generate(height, width, mineCnt)
            val mineCnt = List(45) { number ->
                val coordinate = Coordinate(number / width.value, number % width.value)
                fields.getField(coordinate)::class == Mine::class
            }.count { it }

            Then("지뢰 최대 수만큼 지뢰가 존재한다.") {
                mineCnt shouldBe 10
            }
        }
    }
})
