package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cells
import minesweeper.domain.point.Point

class Board private constructor(
    val cells: Cells,
    val width: Int,
    val height: Int,
    val countOfMine: Int,
) {
    init {
        require(countOfMine <= height * width) { "지뢰는 지도 크기 보다 작아야 합니다." }
        require(countOfMine >= 1) { "최소 한 개 이상의 지뢰가 필요 합니다." }
    }

    fun open(point: Point): Cell = cells.open(point)

    fun isClear(): Boolean = cells.notOpenedCells().size == countOfMine

    companion object {
        fun of(width: Int, height: Int, countOfMine: Int, strategy: BoardCellsCreationStrategy): Board {
            val cells = strategy.create(width, height, countOfMine)
            return Board(cells, width, height, countOfMine)
        }
    }
}
