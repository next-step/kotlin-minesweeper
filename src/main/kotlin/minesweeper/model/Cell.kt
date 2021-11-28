package minesweeper.model

sealed class Cell {

    abstract val position: Position

    val row: Row get() = position.row

    val column: Column get() = position.column

    data class Blank(override val position: Position) : Cell() {

        constructor(row: Row, column: Column) : this(Position(row, column))
    }

    data class Mine(override val position: Position) : Cell() {

        constructor(row: Row, column: Column) : this(Position(row, column))
    }
}
