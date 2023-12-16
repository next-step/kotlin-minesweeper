package minesweeper.model.vison

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Coordinate

interface VisionStrategy {
    fun coordinates(boardLimit: BoardLimit): Set<Coordinate>
}
