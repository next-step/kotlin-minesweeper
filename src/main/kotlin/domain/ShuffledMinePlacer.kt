package domain

import inteface.MinePlacer

class ShuffledMinePlacer : MinePlacer {
    override fun placeMines(board: Board, minePositions: List<Position>) {
        minePositions.forEach { board.placeMineAt(it) }
    }
}
