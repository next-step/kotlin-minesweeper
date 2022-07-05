package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Coordinates

class MineBoard(
    val cells: List<Cell>,
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    companion object {
        fun create(
            height: MineBoardLength,
            width: MineBoardLength,
            mineCount: MineCount,
        ): MineBoard {
            val coordinates = Coordinates.from(height = height, width = width)
            val cells = coordinates.randomMine(count = mineCount)
            return MineBoard(cells = cells)
        }
    }
}
