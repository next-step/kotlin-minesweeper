package minesweeper.domain

sealed interface Cell

sealed interface MinedCell : Cell

data object UndetonatedMineCell : MinedCell

data object DetonatedMineCell : MinedCell

sealed interface EmptyCell : Cell

data object ClosedEmptyCell : EmptyCell

data object OpenedEmptyCell : EmptyCell
