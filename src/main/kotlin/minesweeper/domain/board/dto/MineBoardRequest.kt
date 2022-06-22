package minesweeper.domain.board.dto

data class MineBoardRequest(
    val width: Int,
    val height: Int,
    val numberOfMines: Int
)
