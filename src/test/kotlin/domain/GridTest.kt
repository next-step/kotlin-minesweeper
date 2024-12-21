package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class GridTest : StringSpec({
    "지뢰 주변의 빈 셀들에 올바른 숫자 힌트가 추가된다." {
        val height = Height(3)
        val width = Width(3)
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val cells = Cells.create(height.value, width.value, minePositions)
        val grid = Grid(height, width, cells)

        val gridWithHints = grid.withNumberHints()
        val resultCells = gridWithHints.getCells()

        (resultCells[0][1] as? Cell.NumberCell)?.count shouldBe 1
        (resultCells[1][0] as? Cell.NumberCell)?.count shouldBe 1
        (resultCells[1][1] as? Cell.NumberCell)?.count shouldBe 2
    }

    "숫자 힌트가 추가되어도 지뢰의 위치는 변하지 않는다." {
        val height = Height(3)
        val width = Width(3)
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val cells = Cells.create(height.value, width.value, minePositions)
        val grid = Grid(height, width, cells)

        val gridWithHints = grid.withNumberHints()
        val resultCells = gridWithHints.getCells()

        resultCells[0][0].shouldBeInstanceOf<Cell.MineCell>()
        resultCells[2][2].shouldBeInstanceOf<Cell.MineCell>()
    }
})
