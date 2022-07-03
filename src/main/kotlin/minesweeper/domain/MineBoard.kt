package minesweeper.domain

import minesweeper.domain.cell.Cell

class MineBoard(
    val cells: List<Cell>
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }
}
