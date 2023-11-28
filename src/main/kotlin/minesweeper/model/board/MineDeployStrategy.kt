package minesweeper.model.board

import minesweeper.model.point.Points

interface MineDeployStrategy {
    fun deployPoints(boardLimit: BoardLimit): Points
}
