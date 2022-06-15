package minesweeper.domain.board

data class MineBoardRequest(
    val width: Int,
    val height: Int,
    val numberOfMines: Int
)
