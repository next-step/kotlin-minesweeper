package minesweeper.model.board

import minesweeper.model.board.impl.EvenlyStrategy
import minesweeper.model.point.Points

class Board(
    val points: Points,
    val verticalSize: Int,
    val horizontalSize: Int,
) {

    constructor(
        mineCount: Int,
        verticalSize: Int,
        horizontalSize: Int,
    ) : this(
        points = EvenlyStrategy(mineCount).deployPoints(verticalSize, horizontalSize),
        verticalSize = verticalSize,
        horizontalSize = horizontalSize
    )

    fun minesCount(): Int {
        return points.countOfMine()
    }
}
