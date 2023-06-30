package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({
    test("지뢰찾기 보드를 만든다.") {
        val minePositions: List<Position> = listOf(
            Position(1, 1),
            Position(2, 2),
            Position(3, 3),
        )

        val mineBoard = MineBoard.init(
            height = 3,
            width = 3,
            mineCount = 3,
            minePositionGenerator = { _, _, _ -> minePositions },
        )

        mineBoard.rows shouldBe listOf(
            Row(listOf(Cell.MINE, Cell.CLOSED, Cell.CLOSED)),
            Row(listOf(Cell.CLOSED, Cell.MINE, Cell.CLOSED)),
            Row(listOf(Cell.CLOSED, Cell.CLOSED, Cell.MINE)),
        )
    }
})
