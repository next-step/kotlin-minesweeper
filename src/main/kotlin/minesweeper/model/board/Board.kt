package minesweeper.model.board

import minesweeper.model.point.Points

class Board(
    val points: Points,
    val verticalSize: Int,
    val horizontalSize: Int,
) {

    constructor(
        verticalSize: Int,
        horizontalSize: Int,
        strategy: MineDeployStrategy
    ) : this(
        points = strategy.deployPoints(verticalSize, horizontalSize),
        verticalSize = verticalSize,
        horizontalSize = horizontalSize
    )

    fun countOfMine(): Int {
        return points.countOfMine()
    }
}
