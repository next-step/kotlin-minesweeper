package minesweeper.model.board

import minesweeper.model.point.Points

class Board(
    val points: Points,
    val verticalSize: Int,
    val horizontalSize: Int,
) {

    constructor(v: Int, h: Int, s: MineDeployStrategy) : this(s.deployPoints(v, h), v, h)

    fun countOfMine(): Int {
        return points.countOfMine()
    }
}
