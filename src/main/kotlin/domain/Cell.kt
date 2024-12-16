package domain

sealed interface Cell {
    data class MineCell(private val row: Int, private val col: Int) : Cell
    data class EmptyCell(private val row: Int, private val col: Int) : Cell
}