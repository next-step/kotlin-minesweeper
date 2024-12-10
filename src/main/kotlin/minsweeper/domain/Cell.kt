package minsweeper.domain

sealed interface Cell {
    data object Island : Cell
    data object Mine : Cell
}
