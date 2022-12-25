package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

internal class CellGeneratorTest : StringSpec({
    "높이 3 너비 3 의 보드가 있을 때 2개의 지뢰가 있다면 (1,1), (3,3)에 위치한다." {
        val row = 3
        val mineLocations = listOf(0, 8)
        val generator = MineGenerator(locations = mineLocations, row = Row(row))

        val mineCells = generator.generate()

        mineCells shouldContainAll mineCellListOf(1 to 1, 3 to 3)
    }

    "높이 3 너비 3 의 보드가 있을 때 2개의 지뢰가 (1,1), (3,3) 에 위치한다면 나머지 칸은 빈칸이다" {
        val row = 3

        val blankLocations = listOf(1, 2, 3, 4, 5, 6, 7)

        val generator = BlankGenerator(locations = blankLocations, row = Row(row))
        val blankCells = generator.generate()

        blankCells shouldContainAll blankCellListOf(
            1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3, 3 to 1, 3 to 2
        )
    }
})
