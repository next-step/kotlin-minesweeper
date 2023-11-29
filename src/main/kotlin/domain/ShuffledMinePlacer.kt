package domain

import inteface.MinePlacer

class ShuffledMinePlacer : MinePlacer {
    override fun placeMines(board: Board, mineCount: Int) {
        val allPositions = (0 until board.height).flatMap { y ->
            (0 until board.width).map { x -> Position(x, y) }
        }.shuffled()

        allPositions.take(mineCount).forEach { board.placeMineAt(it) }
    }
}
