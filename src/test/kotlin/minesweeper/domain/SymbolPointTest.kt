package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.SymbolType.EIGHT
import minesweeper.domain.SymbolType.FIVE
import minesweeper.domain.SymbolType.FOUR
import minesweeper.domain.SymbolType.ONE
import minesweeper.domain.SymbolType.SEVEN
import minesweeper.domain.SymbolType.SIX
import minesweeper.domain.SymbolType.THREE
import minesweeper.domain.SymbolType.TWO
import minesweeper.domain.SymbolType.ZERO

class SymbolPointTest : FunSpec({

    test("포인트의 심볼 정보를 업데이트할 수 있다.") {
        listOf(
            0 to ZERO,
            1 to ONE,
            2 to TWO,
            3 to THREE,
            4 to FOUR,
            5 to FIVE,
            6 to SIX,
            7 to SEVEN,
            8 to EIGHT
        ).forEach { (current, expected) ->
            val actual = SymbolPoint(x = 1, y = 1, symbol = ZERO)

            actual.updateSymbol(current)
            actual.getSymbol() shouldBe expected
        }
    }
})
