package minesweeper.model.vison.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Vision
import minesweeper.model.vison.VisionStrategy

class VisionVeiledStrategy(
    private val boardLimit: BoardLimit,
) : VisionStrategy {
    override fun toData(): Map<Coordinate, Vision> {
        TODO("Not yet implemented")
    }
}
