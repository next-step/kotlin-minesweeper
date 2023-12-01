package inteface

import domain.Board
import domain.Position

interface MinePlacer {
    fun placeMines(board: Board, minePositions: List<Position>)
}
