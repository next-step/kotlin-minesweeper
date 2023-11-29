package inteface

import domain.Board

interface MinePlacer {
    fun placeMines(board: Board, mineCount: Int)
}
