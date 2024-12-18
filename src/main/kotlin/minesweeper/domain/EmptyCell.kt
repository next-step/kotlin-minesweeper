package minesweeper.domain

sealed interface EmptyCell : Cell

data object ClosedEmptyCell : EmptyCell

data object OpenedEmptyCell : EmptyCell
