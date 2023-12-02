package domain

import inteface.MineCounter

class AdjacentMineCounter : MineCounter {
    override fun countMinesAround(board: Board, position: Position, height: Int, width: Int): Int {
        val neighborPositions = NeighborPositions(position, height, width)
        return neighborPositions.positions.count { board.hasMineAt(it) }
    }
}
