package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellTest : StringSpec ({
    "셀은 높이와 너비로 생성할 수 있다" {
        // Arrange:
        val height = Height(1)
        val width = Width(1)

        // Act:
        val cell = Cell(height, width)

        // Assert:
        cell.height shouldBe Height(1)
        cell.width shouldBe Width(1)
    }

    // FIXME: O(n)을 사용하면서도 minePosition의 값을 사용하도록 수정하면 매번 객체 생성을 하지 않아도 될 듯
    "지뢰 셀을 생성할 수 있다" {

        // Arrange:
        val height = Height(1)
        val width = Width(1)
        val cell = Cell(height, width, true)

        // Act:
        val mineCell = cell.createMineCell()

        // Assert:
        mineCell.isMine shouldBe true
    }
})
