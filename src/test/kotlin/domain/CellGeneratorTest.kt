package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll

internal class CellGeneratorTest : StringSpec({
    "높이 3 너비 3 의 보드가 있을 때 2개의 지뢰가 있다면 (1,1), (3,3)에 위치한다." {
        val row = 3

        val mineLocations = listOf(0, 8)
        val generator = CellGenerator(mineLocations = mineLocations, row = Row(row))

        val mineCells = generator.minesGenerate()
        mineCells shouldContainAll listOf(
            Mine(Coordinate(Row(1), Column(1))),
            Mine(Coordinate(Row(3), Column(3)))
        )
    }

    "높이 3 너비 3 의 보드가 있을 때 2개의 지뢰가 (1,1), (3,3) 에 위치한다면 나머지 칸은 빈칸이다" {
        val row = 3

        val blankLocations = listOf(1, 2, 3, 4, 5, 6, 7)

        val generator = CellGenerator(blankLocations = blankLocations, row = Row(row))
        val blankCells = generator.blanksGenerate()

        blankCells shouldContainAll listOf(
            Blank(Coordinate(Row(1), Column(2))),
            Blank(Coordinate(Row(1), Column(3))),
            Blank(Coordinate(Row(2), Column(1))),
            Blank(Coordinate(Row(2), Column(2))),
            Blank(Coordinate(Row(2), Column(3))),
            Blank(Coordinate(Row(3), Column(1))),
            Blank(Coordinate(Row(3), Column(2)))
        )
    }
})
