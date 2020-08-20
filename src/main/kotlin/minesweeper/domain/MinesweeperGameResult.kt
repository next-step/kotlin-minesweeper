package minesweeper.domain

val <T> T.exhaustive: T
    get() = this

sealed class MinesweeperGameResult {

    data class Success(val height: String, val width: String, val mineCount: String) : MinesweeperGameResult()
    data class Error(val cause: Exception? = null) : MinesweeperGameResult()
    object InvalidHeight : MinesweeperGameResult()
    object InvalidWidth : MinesweeperGameResult()
    object InvalidMineCount : MinesweeperGameResult()

    fun getMessage(result: MinesweeperGameResult): String {
        return when (result) {
            is Success -> "지뢰판이 만들어졌습니다."
            is Error -> "에러가 발생했습니다."
            is InvalidHeight -> "높이는 자연수로 입력해주세요."
            is InvalidWidth -> "너비는 자연수로 입력해주세요."
            is InvalidMineCount -> "지뢰개수는 자연수로 입력해주세요."
        }
    }
}
