package tdd.minesweeper.provider

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.type.SymbolType
import java.lang.IllegalArgumentException

class DefaultRowsProviderTest : FunSpec({

    test("심볼 좌표 정보 2차원 리스트를 생성한다.") {
        val rows = DefaultRowsProvider.provide(Area(5, 5), 5)

        val allRows = rows.findByFilter { true }
        val filteredRows = rows.findByFilter { it.equalsSymbol(SymbolType.MINE) }

        filteredRows shouldHaveSize 5
        allRows shouldHaveSize 25
    }

    test("지뢰 갯수가 영역 정보의 넓이보다 많을 경우 예외를 던진다. ") {
        shouldThrow<IllegalArgumentException> {
            DefaultRowsProvider.provide(Area(5, 5), 26)
        }
    }
})
