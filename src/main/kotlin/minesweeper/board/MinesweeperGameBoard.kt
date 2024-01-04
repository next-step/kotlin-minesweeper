package minesweeper.board

import minesweeper.cell.Cell
import minesweeper.position.Position

data class MinesweeperGameBoard(
    override val board: List<List<Cell>>
): GameBoard() {

    override fun visit(position: Position) {
        throw UnsupportedOperationException()
    }

    override fun isExistMinePosition(positions: List<Position>): Boolean =
        positions.any { board[it.col][it.row].isMine() }

    override fun areAllCellsOpened(): Boolean = false
}
