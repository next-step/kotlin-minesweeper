package minesweeper.domain

sealed interface Cell

sealed interface MinedCell : Cell

class UndetonatedMineCell : MinedCell

class DetonatedMineCell : MinedCell

sealed interface EmptyCell : Cell

class UnopenedCell : EmptyCell

class OpenedCell : EmptyCell
