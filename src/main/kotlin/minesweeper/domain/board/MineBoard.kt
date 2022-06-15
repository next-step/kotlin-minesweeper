package minesweeper.domain.board

class MineBoard private constructor(
    private val width: Int,
    private val height: Int,
    private val numberOfMines: Int
) {

    init {
        val size = width * height
        require(width > 0 && height > 0 && numberOfMines > 0) { "width, height and number of mines must be positive." }
        require(size >= numberOfMines) { "number of mines can't exceed the size of board." }
    }

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int) = MineBoard(width, height, numberOfMines)
    }
}
