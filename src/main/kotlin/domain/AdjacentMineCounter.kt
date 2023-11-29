package domain

import inteface.MineCounter

class AdjacentMineCounter : MineCounter {
    override fun countMinesAround(board: Board, position: Position): Int {
        return position.getNeighborPositions(board.height, board.width)
            .count { board.hasMineAt(it) }
    }

    private fun Position.getNeighborPositions(boardHeight: Int, boardWidth: Int): List<Position> {
        return (-1..1).flatMap { dx ->
            (-1..1).mapNotNull { dy ->
                Position(x + dx, y + dy)
                    .takeIf { it != this && it.isWithinBounds(boardHeight, boardWidth) }
            }
        }
    }

    private fun Position.isWithinBounds(boardHeight: Int, boardWidth: Int): Boolean =
        x in 0 until boardWidth && y in 0 until boardHeight
}
