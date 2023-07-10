package minesweeper.domain.cell

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.point.Point

class CellTest : ShouldSpec({
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
