package minesweeper.domain

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
    }
})
