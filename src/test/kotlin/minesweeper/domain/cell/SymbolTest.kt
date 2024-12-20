package minesweeper.domain.cell

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class SymbolTest : BehaviorSpec({
    given("심볼은") {
        `when`("인접 지뢰 개수 0개를 입력 받으면") {
            val result = Symbol.from(NumberOfAdjacentMines.ZERO)

            then("표시 심볼도 0개를 뜻한다") {
                result shouldBe Symbol.ZERO
            }
        }

        `when`("입력받은 인접 지뢰 개수에 따라") {
            then("해당 인접 지뢰 개수를 뜻하는 심볼을 보여준다") {
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
