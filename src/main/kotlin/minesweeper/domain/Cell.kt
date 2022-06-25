package minesweeper.domain

sealed class Cell

object Mine : Cell()

object Opened : Cell()
