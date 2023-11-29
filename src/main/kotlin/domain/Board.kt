package domain

class Board(height: Int, width: Int) {
    private val cells: List<Cell> = List(height * width) { index ->
        Cell(Position(index % width, index / width))
    }

    fun placeMineAt(position: Position) {
        findCell(position).placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).isMine()

    fun countMines(): Int = cells.count { it.isMine() }

    private fun findCell(position: Position): Cell = cells.first { it.position == position }
}
