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

class MinePointTest : FunSpec({

    test("지뢰 좌표 정보의 심볼이 0~7이라면 다음 심볼로 업데이트할 수 있다.") {
        listOf(
            ZERO to ONE,
            ONE to TWO,
            TWO to THREE,
            THREE to FOUR,
            FOUR to FIVE,
            FIVE to SIX,
            SIX to SEVEN,
            SEVEN to EIGHT
        ).forEach { (current, expected) ->
            val actual = MinePoint(x = 1, y = 1, symbol = current)

            actual.updateNextSymbol()
            actual.symbol shouldBe expected
        }
    }
})
