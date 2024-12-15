package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class LocationTest : BehaviorSpec({
    given("Location 은 ") {
        val row = 1
        val column = 1

        `when`("row 값과 column 값을 받아") {
            val sut = Location(row, column)

            then("생성할 수 있다") {
                sut.row shouldBe row
                sut.column shouldBe column
            }
        }
    }
})
