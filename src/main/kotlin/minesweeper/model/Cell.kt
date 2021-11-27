package minesweeper.model

sealed class Cell {

    abstract val row: Row

    abstract val column: Column

    data class Blank(override val row: Row, override val column: Column) : Cell()

    data class Mine(override val row: Row, override val column: Column) : Cell()
}
