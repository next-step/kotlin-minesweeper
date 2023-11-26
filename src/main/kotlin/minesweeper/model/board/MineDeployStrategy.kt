package minesweeper.model.board

import minesweeper.model.point.Points

interface MineDeployStrategy {
    fun deployPoints(v: Int, h: Int): Points
}
