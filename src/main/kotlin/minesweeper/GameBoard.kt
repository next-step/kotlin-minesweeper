package minesweeper

class GameBoard(
    val height: Int,
    val width: Int,
    val mineNumber: Int,
    private val mineLocationGenerator: MineLocationGenerator
) {
    private var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }

    init {
        generateMine(mineNumber, width, height)
    }

    private fun generateMine(mineNumber: Int, maximumOfX: Int, maximumOfY: Int) {
        repeat(mineNumber) {
            mineLocationGenerator.generateMineLocation(maximumOfX, maximumOfY)
        }
    }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
