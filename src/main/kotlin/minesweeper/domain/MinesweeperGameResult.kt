package minesweeper.domain

val <T> T.exhaustive: T
    get() = this

sealed class MinesweeperGameResult {
    data class Success(val height: String, val width: String, val mineCount: String) : MinesweeperGameResult()
    object InvalidHeight : MinesweeperGameResult()
    object InvalidWidth : MinesweeperGameResult()
    object InvalidMineCount : MinesweeperGameResult()
}
