package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BoardTest : StringSpec({
    "인자로 받은 좌표가 지뢰라면 true 를 반환한다." {
        val mineCells = mineCellListOf(1 to 1)
        val board = Board(mineCells)
        val result = board.isMineCell(coordinateOf(1, 1))

        result shouldBe true
    }

    "인자로 받은 좌표가 빈칸이거나 없는 좌표라면 false 를 반환한다." {
        val blankCells = blankCellListOf(1 to 2, 1 to 3)
        val board = Board(blankCells)

        val coordinates = coordinateListOf(
            1 to 1, 5 to 3, 3 to 3
        )
        coordinates.forAll {
            val result = board.isMineCell(it)
            result shouldBe false
        }
    }
})
