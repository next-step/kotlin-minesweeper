package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.Position

data class BoardArea(private val height: BoardHeight, private val width: BoardWidth) {

    operator fun contains(position: Position): Boolean = inHeight(position.y) && inWidth(position.x)

    fun inHeight(y: Int) = height.inRange(y)

    fun inWidth(x: Int) = width.inRange(x)

    fun canHave(count: Int): Boolean = count <= area()

    fun area(): Int = width() * height()

    fun width(): Int = width.width()

    fun height(): Int = height.height()

    companion object {
        fun of(height: Int, width: Int): BoardArea = BoardArea(BoardHeight(height), BoardWidth(width))
    }

}
