package minesweeper.domain

sealed class MinesweeperGameResult {
    data class Success(val height: String, val width: String, val mineCount: String) : MinesweeperGameResult()
    data class Error(val message: String, val cause: Exception? = null) : MinesweeperGameResult()
}
