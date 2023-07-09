package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.ClearCell
import minesweeper.domain.point.Point

class RandomBasedCreationStrategy(
    override val height: Int,
    override val width: Int,
    override val countOfMine: Int,
) : BoardCellsCreationStrategy {

    init {
        require(countOfMine <= height * width) { "지뢰는 지도 크기 보다 작아야 합니다." }
        require(countOfMine >= 1) { "최소 한 개 이상의 지뢰가 필요 합니다." }
    }

    override fun create(): Cells {
        val points = Point.square(height, width)
        val minePoints = points.shuffled().take(countOfMine)
        val cells = Cells()

        points
            .map { ClearCell(it) }
            .forEach { cells.add(it) }

        minePoints.forEach { cells.createMine(it) }

        cells.closeAll()

        return cells
    }
}
