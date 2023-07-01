package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RowTest : FunSpec({
    test("지뢰찾기 보드 row 번째 행을 만든다.") {
        val mineCoordinates: Set<Coordinate> = setOf(
            Coordinate(1, 1),
            Coordinate(1, 2),
        )

        val row = Row.of(
            width = 3,
            row = 1,
            mineCoordinates = mineCoordinates,
        )

        row shouldBe Row(listOf(Cell.MINE, Cell.MINE, Cell.CLOSED))
    }
})
