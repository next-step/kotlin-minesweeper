package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RowTest : BehaviorSpec({
    given("Row 는") {
        val cells = List(3) { BasicCell(row = 1, column = (it + 1)) }
        val sut = Row(cells)

        `when`("cells() 를 하면") {
            then("cells 를 반환한다") {
                sut.cells() shouldBe cells
            }
        }

        `when`("Location 으로 일치하는 셀을 찾으면") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 셀을 반환한다") {
                result.location() shouldBe location
            }
        }

        `when`("Location 으로 일치하는 셀을 찾지 못하면") {
            val location = Location(row = 2, column = 1)
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    sut.find(location)
                }
            }
        }
    }
})
