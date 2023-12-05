package minesweeper.model.board.minedeploy

import minesweeper.model.board.BoardLimit
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate

interface MineDeployStrategy {
    fun deployPoints(boardLimit: BoardLimit): Map<Coordinate, Attribute>
}
