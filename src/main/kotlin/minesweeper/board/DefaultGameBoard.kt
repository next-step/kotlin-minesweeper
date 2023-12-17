package minesweeper.board

import minesweeper.cell.Cell
import minesweeper.position.Position

data class DefaultGameBoard(
    override val board: List<List<Cell>>,
    private var closedCellCount: Int
): GameBoard() {

    override fun visit(position: Position) {
        closedCellCount--
        board[position.col][position.row].visit()
    }

    override fun isExistMinePosition(positions: List<Position>): Boolean = false

    override fun areAllCellsOpened(): Boolean = closedCellCount == 0
}
