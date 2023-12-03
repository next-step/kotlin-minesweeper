package minesweeper.domain

sealed class Cell

object MineCell : Cell()

object EmptyCell : Cell()
