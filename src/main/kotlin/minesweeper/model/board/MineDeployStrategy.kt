package minesweeper.model.board

import minesweeper.model.point.Points

interface MineDeployStrategy {
    fun deployPoints(verticalLimit: Int, horizontalLimit: Int): Points
}
