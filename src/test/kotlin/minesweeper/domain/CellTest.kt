package minesweeper.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class CellTest: ShouldSpec({
    should("MineCell은 mine 속성이 true이다.") {
        MineCell(Point(1, 1)).mine shouldBe true
    }

    should("ClearCell은 mine 속성이 false이다.") {
        ClearCell(Point(1, 1)).mine shouldBe false
    }

    should("Cell은 Point 오름차순으로 정렬 된다.") {
        val cells = listOf(
            ClearCell(Point(0, 0)), ClearCell(Point(1, 0)),
            ClearCell(Point(0, 1)), ClearCell(Point(1, 1)),
        )
        val sortedCells = cells.shuffled().sorted()
        sortedCells[0].point shouldBe Point(0, 0)
        sortedCells[1].point shouldBe Point(1, 0)
        sortedCells[2].point shouldBe Point(0, 1)
        sortedCells[3].point shouldBe Point(1, 1)
    }
})
