package minesweeper.domain.board

import minesweeper.domain.Block
import minesweeper.domain.Coordinate

class MinesweeperBoard(private val board: Map<Coordinate, Block>) {

    fun sortedBlocks(): List<Block> = board.values.sortedBy { it.coordinate }
}
