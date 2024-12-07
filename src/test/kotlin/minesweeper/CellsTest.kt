package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellsTest : StringSpec({
    "셀은 한 개 이상이면 생성 가능하다." {
        // Arrange:
        val height = Height(1)
        val width = Width(1)
        val cell = Cell(height, width)

        // Act:
        val cells = Cells(listOf(cell))

        // Assert:
        cells.cells.size shouldBe 1
    }

    "셀이 한 개도 없으면 예외가 발생한다." {
        // Arrange:
        val emptyCells = emptyList<Cell>()

        // Act:
        val result = shouldThrow<IllegalArgumentException> {
            Cells(emptyCells)
        }

        // Assert:
        result.message shouldBe "셀은 1개 이상 이어야 합니다"
    }

    "높이와 너비로 셀(높이 x 너비)이 생성된다." {
        // Arrange:
        val heightRange = 1..2
        val widthRange = 1..2

        // Act:
        val cells = Cells.generate(heightRange, widthRange)

        // Assert:
        cells.cells.size shouldBe 4
    }

    "빈 셀에 지뢰를 놓을 수 있다." {
        // Arrange:
        val cell = Cell(Height(1), Width(1))
        val cells = Cells(listOf(cell))
        val minePositions = listOf(Cell(Height(1), Width(1)))

        // Act:
        val result = cells.placeMines(minePositions)

        // Assert:
        result.cells[0].isMine shouldBe true
    }
})
