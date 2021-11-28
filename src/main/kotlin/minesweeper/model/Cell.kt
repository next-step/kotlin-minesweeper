package minesweeper.model

sealed class Cell {

    abstract val position: Position

    val row: Row get() = position.row

    val column: Column get() = position.column

    data class Blank(override val position: Position) : Cell()

    data class Mine(override val position: Position) : Cell()
}
