package minesweeper.model.vison.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Coordinate
import minesweeper.model.point.toCoordinate
import minesweeper.model.vison.VisionStrategy

object VisionCoveredStrategy : VisionStrategy {
    override fun coordinates(boardLimit: BoardLimit): Set<Coordinate> {
        return boardLimit.verticalRange().flatMap { vertical ->
            boardLimit.horizontalRange().map { horizontal ->
                (vertical to horizontal).toCoordinate()
            }
        }.toSet()
    }
}
