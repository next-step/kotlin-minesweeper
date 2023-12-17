package minesweeper.board

import minesweeper.position.Position

data class BoardElement (
    val height: Int,
    val width: Int
) {

    fun isOutOfRange(position: Position) =
        position.row < 0 || position.col < 0 || position.row >= this.height || position.col >= this.width

}
