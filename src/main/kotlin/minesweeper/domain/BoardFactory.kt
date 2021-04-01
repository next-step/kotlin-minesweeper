package minesweeper.domain

class BoardFactory(
    private val width: Int,
    private val height: Int,
    private val cellFactory: CellFactory = CellFactory.Default(width * height)
) {
    fun board(bomb: Int): Board {
        require(bomb > 0)

        val matrix = Matrix(width, height)
        return Board(cellFactory.cells(bomb, matrix), matrix)
    }
}
