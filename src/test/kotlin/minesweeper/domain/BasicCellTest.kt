package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BasicCellTest : BehaviorSpec({
    given("BasicCell 은") {
        val sut = BasicCell(row = 1, column = 1)

        `when`("생성하면") {
            then("자신의 위치를 알 수 있다") {
                val result = sut.location()
                result shouldBe Location(row = 1, column = 1)
            }

            then("기본 심볼은 CLOSED 이다") {
                sut.symbol() shouldBe Symbol.CLOSED
            }
        }
    }
})
