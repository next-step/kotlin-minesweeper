package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinesweeperTest : StringSpec({

    "지뢰찾기는 높이와 너비로 셀이 생성된다." {
        // Arrange:
        val height = 3
        val width = 3

        // Act:
        val cell = Minesweeper.setUp(height, width)

        // Assert:
        cell.cells.cells.size shouldBe 9
    }

    "생성된 셀 내에서 지뢰의 위치를 생성한다." {
        // Arrange:
        val minesweeper = Minesweeper.setUp(3, 3)
        val cells = minesweeper.cells

        // Act:
        val mineCells = minesweeper.chooseMineCells(1)

        // Assert:
        mineCells.size shouldBe 1
        mineCells.all { mineCell -> cells.cells.contains(mineCell) } shouldBe true
    }
})
