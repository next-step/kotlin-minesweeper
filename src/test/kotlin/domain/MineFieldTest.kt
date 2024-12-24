package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineFieldTest : StringSpec({
    "올바른 크기와 지뢰 개수로 지뢰밭이 생성된다." {
        val height = Height(3)
        val width = Width(3)
        val mineCount = 3

        val mineField = MineField(height, width, mineCount)
        val state = mineField.getState()

        state.cells.size shouldBe 3
        state.cells[0].size shouldBe 3
    }

    "지뢰 개수가 셀의 총 개수를 초과하면 예외가 발생한다." {
        val height = Height(3)
        val width = Width(3)
        val invalidMineCount = 10

        shouldThrow<IllegalArgumentException> {
            MineField(height, width, invalidMineCount)
        }
    }

    "지뢰 주변의 빈 셀들에 올바른 숫자 힌트가 추가된다." {
        val height = Height(3)
        val width = Width(3)
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val cells = Cells.create(height.value, width.value, minePositions).addNumberHints()

        cells.isCellNumber(0, 1, 1) shouldBe true
        cells.isCellNumber(1, 0, 1) shouldBe true
        cells.isCellNumber(1, 1, 2) shouldBe true
    }

    "숫자 힌트가 추가되어도 지뢰의 위치는 변하지 않는다." {
        val height = Height(3)
        val width = Width(3)
        val minePositions = setOf(Position(0, 0), Position(2, 2))
        val cells = Cells.create(height.value, width.value, minePositions).addNumberHints()

        cells.isCellMine(0, 0) shouldBe true
        cells.isCellMine(2, 2) shouldBe true
    }
})

fun Cells.isCellNumber(
    row: Int,
    column: Int,
    expectedCount: Int,
): Boolean {
    val cell = _rows[row][column]
    return cell is Cell.NumberCell && cell.count == expectedCount
}
