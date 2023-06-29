package minesweeper.domain

data class GameBoardSize(
    val height: Int,
    val width: Int,
) {
    fun getLinearSize(): Int {
        return height * width
    }
}
