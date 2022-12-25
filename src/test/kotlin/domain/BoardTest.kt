package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BoardTest : StringSpec({
    "보드에 지뢰개수만큼 지뢰가 생긴다." {
        val row = Row(3)
        val column = Column(3)
        val mineCount = MineCount(4)

        val board = Board.from(row, column, mineCount)

        board.cells.filterIsInstance<Mine>().size shouldBe mineCount.value
    }

    "지뢰 개수를 제외한 나머지 칸은 빈칸이다." {
        val row = Row(3)
        val column = Column(3)
        val mineCount = MineCount(4)

        val board = Board.from(row, column, mineCount)

        board.cells.filterIsInstance<Blank>().size shouldBe (row * column) - mineCount.value
    }

    "인자로 받은 좌표가 지뢰라면 true 를 반환한다." {
        val mineLocations = listOf(0)
        val generator = MineGenerator(locations = mineLocations, row = Row(3))

        val mineCells = generator.generate()
        val board = Board(mineCells)
        val result = board.isMineCell(coordinateOf(1, 1))

        result shouldBe true
    }

    "인자로 받은 좌표가 빈칸이거나 없는 좌표라면 false 를 반환한다." {
        val blankLocations = listOf(0)
        val generator = BlankGenerator(locations = blankLocations, row = Row(3))

        val blankCells = generator.generate()
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
