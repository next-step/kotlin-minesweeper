package minesweeper.domain

sealed interface Cell

object Mine : Cell
data class Empty(val mineCount: Int = 0) : Cell
