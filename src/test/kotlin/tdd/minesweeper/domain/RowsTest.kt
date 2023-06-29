package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.type.SymbolType
import tdd.minesweeper.fixture.create3x3Rows

class RowsTest: FunSpec({

    test("유효한 좌표 정보로 심볼 좌표 정보를 조회할 수 있다.") {
        val rows = create3x3Rows()

        val actual: SymbolPoint = rows.findByPoint(Point(1,1))

        actual.point shouldBe Point(1,1)
        actual.equalsSymbol(SymbolType.TWO) shouldBe true
    }

    test("유효하지 않은 좌표 정보는 예외를 던진다.") {
        val rows = create3x3Rows()

        listOf(Point(4,0), Point(4,4), Point(0,4)).forEach {
            shouldThrow<IllegalArgumentException> {
                rows.findByPoint(it)
            }
        }
    }

    test("심볼 좌표 목록 검색 기능을 제공한다.") {
        val rows = create3x3Rows()

        val actual: List<SymbolPoint> = rows.findByFilter {
            it.equalsSymbol(SymbolType.MINE)
        }

        actual shouldHaveSize 2
    }
})
