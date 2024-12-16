package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BasicCellTest : BehaviorSpec({
    given("BasicCell 은") {
        val row = 1
        val column = 1

        `when`("생성하면") {
            val sut = BasicCell(row = row, column = column)

            then("자신의 위치를 알 수 있다") {
                val result = sut.location()
                val expectedLocation = Location(row = row, column = column)

                result shouldBe expectedLocation
            }

            then("기본 심볼은 CLOSED 이다") {
                sut.symbol() shouldBe Symbol.CLOSED
            }
        }
    }
})
