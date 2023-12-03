package minesweeper.model.board.impl

import minesweeper.model.board.BoardLimit
import minesweeper.model.board.MineDeployStrategy
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

class SpecifiedCoordinatesStrategy(
    val coordinates: List<Coordinate>,
) : MineDeployStrategy {

    constructor(vararg pairs: Pair<Int, Int>) : this(
        pairs.map {
            Coordinate(
                Vertical(it.first),
                Horizontal(it.second)
            )
        }
    )

    override fun deployPoints(boardLimit: BoardLimit): Map<Coordinate, Attribute> {
        TODO("Not yet implemented")
    }
}
