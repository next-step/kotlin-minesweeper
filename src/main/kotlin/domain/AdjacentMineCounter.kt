package domain

import inteface.MineCounter

class AdjacentMineCounter : MineCounter {
    override fun countMinesAround(board: Board, position: Position): Int {
        val neighborPositions = NeighborPositions(position, board.height, board.width)
        return neighborPositions.positions.count { neighborPosition ->
            board.findCell(neighborPosition)?.isMine ?: false
        }
    }
}
