package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class SymbolTest : BehaviorSpec({
    given("Symbol.from 은") {
        `when`("NumberOfAdjacentMines.ZERO를 입력받으면") {
            val result = Symbol.from(NumberOfAdjacentMines.ZERO)

            then("Symbol.ZERO 를 반환한다") {
                result shouldBe Symbol.ZERO
            }
        }

        `when`("NumberOfAdjacentMines 의 각 숫자를 입력 받을 때마다") {
            then("적절한 Symbol 을 반환한다") {
                Symbol.from(NumberOfAdjacentMines(1)) shouldBe Symbol.ONE
                Symbol.from(NumberOfAdjacentMines(2)) shouldBe Symbol.TWO
                Symbol.from(NumberOfAdjacentMines(3)) shouldBe Symbol.THREE
                Symbol.from(NumberOfAdjacentMines(4)) shouldBe Symbol.FOUR
                Symbol.from(NumberOfAdjacentMines(5)) shouldBe Symbol.FIVE
                Symbol.from(NumberOfAdjacentMines(6)) shouldBe Symbol.SIX
                Symbol.from(NumberOfAdjacentMines(7)) shouldBe Symbol.SEVEN
                Symbol.from(NumberOfAdjacentMines(8)) shouldBe Symbol.EIGHT
            }
        }
    }
})
