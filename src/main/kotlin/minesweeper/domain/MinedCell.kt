package minesweeper.domain

sealed interface MinedCell : Cell

data object UndetonatedMineCell : MinedCell

data object DetonatedMineCell : MinedCell
