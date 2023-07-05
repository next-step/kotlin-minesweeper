package domain

import fixture.cell
import fixture.mine
import fixture.row
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RowTest : FunSpec({
    test("지뢰찾기 보드 row 번째 행을 만든다.") {
        val mineCoordinates: Set<Coordinate> = setOf(
            Coordinate(0, 0),
            Coordinate(0, 1),
        )

        val row = Row.of(
            width = 3,
            row = 0,
            mineCoordinates = mineCoordinates,
        )

        row shouldBe row(mine(), mine(), cell())
    }
})
