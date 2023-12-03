package minesweeper.model.vison.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Coordinate
import minesweeper.model.vison.VisionStrategy

class VisionVeiledStrategy(
    private val boardLimit: BoardLimit,
) : VisionStrategy {
    override fun toData(): Set<Coordinate> {
        TODO("Not yet implemented")
    }
}
