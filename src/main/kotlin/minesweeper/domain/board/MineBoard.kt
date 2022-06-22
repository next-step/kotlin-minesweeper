package minesweeper.domain.board

import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.domain.cell.Cells

class MineBoard(
    width: Int,
    height: Int,
    numberOfMines: Int,
    mineStrategy: MineStrategy
) {
    var cells: Cells

    init {
        val size = width * height

        require(width >= 0 && height >= 0) { "property must be zero or positive." }
        require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }

        cells = Cells.of(width, height, numberOfMines, mineStrategy)
        NearbyMineCounter.count(cells)
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
