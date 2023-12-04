package minesweeper.model.vison.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Coordinate
import minesweeper.model.point.toCoordinate
import minesweeper.model.vison.VisionStrategy

object VisionCoveredStrategy : VisionStrategy {
    override fun coordinates(boardLimit: BoardLimit): Set<Coordinate> {
        val set = mutableSetOf<Coordinate>()
        boardLimit.verticalRange().forEach { v ->
            boardLimit.horizontalRange().forEach { h ->
                set.add((v to h).toCoordinate())
            }
        }
        return set
    }
}
