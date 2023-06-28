package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.SymbolType.BLIND
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
            val actual = SymbolPoint(x = 1, y = 1, symbol = ZERO, marked = true)

            actual.updateSymbol(current)
            actual.getSymbol() shouldBe expected
        }
    }

    test("체크가 된 상태면 실제 심볼을 반환한다.") {
        val actual = SymbolPoint(point = Point(0, 0), symbol = ONE, marked = true)

        actual.getSymbol() shouldBe ONE
    }

    test("체크가 안 된 상태면 블라인드 심볼을 반환한다.") {
        val actual = SymbolPoint(point = Point(0, 0), symbol = ONE)

        actual.getSymbol() shouldBe BLIND
    }

    test("체크 여부를 반환하는 기능을 가진다.") {
        val actual = SymbolPoint(point = Point(0, 0), symbol = ONE)

        actual.isMarked() shouldBe false
    }

    test("체크하는 기능을 가진다. ") {
        val actual = SymbolPoint(point = Point(0, 0), symbol = ONE)

        actual.isMarked() shouldBe false

        actual.marking()
        actual.isMarked() shouldBe true
    }
})
