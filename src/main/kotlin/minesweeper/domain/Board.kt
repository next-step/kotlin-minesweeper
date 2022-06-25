package minesweeper.domain

class Board(
    private val boardSize: BoardSize,
    private val minesCount: Int
) {

    init {
        require(boardSize.area > minesCount) { "Mines count[$minesCount] must be smaller than area[${boardSize.area}] of the board" }
    }

    val cells: List<Cell> = (1..boardSize.area).map {
        if (it <= minesCount) Mine
        else Opened
    }.shuffled()

    fun rows(): List<List<Cell>> {
        return cells.chunked(boardSize.width)
    }
}
