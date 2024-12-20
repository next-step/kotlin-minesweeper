package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LocationTest : BehaviorSpec({
    given("row = 1, column = 1 을 가지고") {
        val row = 1
        val column = 1

        `when`("위치를 생성하면") {
            val sut = Location(row, column)

            then("위치는 row = 1, column = 1이다") {
                sut.row shouldBe row
                sut.column shouldBe column
            }
        }
    }
})
