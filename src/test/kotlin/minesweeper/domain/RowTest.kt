package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RowTest : BehaviorSpec({
    given("Row 는") {
        val cells = List(3) { BasicCell(row = 1, column = (it + 1)) }
        val sut = Row(cells)

        `when`("display()를 하면") {
            val result: String = sut.display()

            then("Cell의 모든 display()를 합쳐 하나의 문자열로 만들어 반환한다") {
                result shouldBe cells.joinToString(separator = " ") { it.display() }
            }
        }

        `when`("cells() 를 하면") {
            then("cells 를 반환한다") {
                sut.cells() shouldBe cells
            }
        }
    }
})
