package minesweeper.domain

sealed interface Cell

data class Mine(val mineCount: Int = 0) : Cell
data class Empty(val mineCount: Int = 0) : Cell

