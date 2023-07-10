package minesweeper.domain.board

class GameBoard(val board: List<List<Tile>>) {
    companion object {
        fun from(cellBoard: CellBoard): GameBoard {
            val board = cellBoard.board.map { cells ->
                cellsToTiles(cells, cellBoard)
            }
            return GameBoard(board)
        }

        private fun cellsToTiles(
            cells: List<Cell>,
            cellBoard: CellBoard
        ): List<Tile> {
            return cells.map {
                val neighbors = cellBoard.getNeighbors(it.position)
                Tile(it, neighbors)
            }
        }
    }
}
