package minesweeper.domain.board

class GameBoard(val board: Board<Tile>) {
    companion object {
        fun from(cellBoard: CellBoard): GameBoard {
            val neighborCells: (Cell) -> List<Cell> =
                { cellBoard.getNeighbors(it.position).map { cellBoard.board.get(it) } }
            val board = cellBoard.board
                .map { Tile(it, neighborCells(it)) }
            return GameBoard(board)
        }
    }
}
