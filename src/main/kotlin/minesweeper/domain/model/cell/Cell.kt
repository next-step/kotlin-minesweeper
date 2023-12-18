package minesweeper.domain.model.cell

class Cell(private val cellState: CellState, private val countOfMinesNearby: Int) {

    fun isMine(): Boolean = cellState == CellState.MINE

    fun getCountOfMinesNearby(): Int = countOfMinesNearby

    companion object {
        fun create(cellState: CellState, countOfMinesNearby: Int): Cell {
            return Cell(cellState, countOfMinesNearby)
        }
    }
}
