package tdd.minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.type.SymbolType

class SymbolPointTest : FunSpec({

    test("마킹 여부를 조회할 수 있다.") {
        val actual = SymbolPoint(Point(1, 1), SymbolType.ZERO)

        actual.isMarked shouldBe false
    }

    test("심볼 정보를 수정할 수 있다.") {
        val actual = SymbolPoint(Point(1, 1), SymbolType.ZERO)

        actual.equalsSymbol(SymbolType.TWO) shouldBe false
        actual.updateSymbol(SymbolType.TWO)
        actual.equalsSymbol(SymbolType.TWO) shouldBe true
    }

    test("심볼 정보가 MINE인 경우 수정되지 않는다.") {
        val actual = SymbolPoint(Point(1, 1), SymbolType.MINE)

        actual.updateSymbol(SymbolType.TWO)
        actual.equalsSymbol(SymbolType.TWO) shouldBe false
    }

    test("마킹 가능한지 조회할 수 있다.") {
        val actual = SymbolPoint(Point(1, 1), SymbolType.ONE)

        actual.isMarkable() shouldBe true
    }

    test("이미 마킹된 상태면 마킹 후 false를 반환한다.") {
        val actual = SymbolPoint(Point(1, 1), SymbolType.ONE)

        actual.isMarkable() shouldBe true
        actual.mark() shouldBe true

        actual.isMarkable() shouldBe false
        actual.mark() shouldBe false
    }
})
