package minesweeper.model.board

import minesweeper.model.point.Points

class Board(
    val points: Points,
    val verticalSize: Int,
    val horizontalSize: Int,
) {

    constructor(verticalSize: Int, horizontalSize: Int, s: MineDeployStrategy) : this(
        s.deployPoints(verticalSize, horizontalSize),
        verticalSize,
        horizontalSize
    )

    fun countOfMine(): Int {
        return points.countOfMine()
    }
}
