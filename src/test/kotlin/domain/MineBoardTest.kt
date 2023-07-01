package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({
    test("지뢰찾기 보드를 만든다.") {
        val mineCoordinates: Set<Coordinate> = setOf(
            Coordinate(1, 1),
            Coordinate(2, 2),
            Coordinate(3, 3),
        )

        val mineBoard = MineBoard.create(
            height = 3,
            width = 3,
            mineCount = 3,
            mineCoordinateGenerator = { _ -> mineCoordinates },
        )

        mineBoard.rows shouldBe listOf(
            Row(listOf(Cell.MINE, Cell.CLOSED, Cell.CLOSED)),
            Row(listOf(Cell.CLOSED, Cell.MINE, Cell.CLOSED)),
            Row(listOf(Cell.CLOSED, Cell.CLOSED, Cell.MINE)),
        )
    }
})
