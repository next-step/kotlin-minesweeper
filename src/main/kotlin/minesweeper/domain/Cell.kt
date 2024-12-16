package minesweeper.domain

sealed interface Cell

class MinedCell : Cell

class EmptyCell : Cell
