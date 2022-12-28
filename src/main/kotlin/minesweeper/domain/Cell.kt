package minesweeper.domain

sealed class Cell(
    open val position: Position,
    val state: String,
)
