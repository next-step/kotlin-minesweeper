package minesweeper.model

sealed class Cell {

    object Blank : Cell()

    object Mine : Cell()
}
