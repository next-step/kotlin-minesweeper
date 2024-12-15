package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BasicCellTest : BehaviorSpec({
    given("BasicCell 은") {
        val sut = BasicCell(row = 1, column = 1)

        `when`("기본 상태의 display 값은") {
            val result = sut.display()

            then("'■' 이다") {
                result shouldBe "■"
            }
        }

        `when`("생성하면") {
            val result = sut.location()

            then("자신의 위치를 알 수 있다") {
                result shouldBe Location(row = 1, column = 1)
            }
        }
    }
})
