package minesweeper.domain.board

import minesweeper.domain.vo.Position
import minesweeper.domain.vo.PositiveNumber

class CellBoard(val board: Board<Cell>) {
    fun getNeighbors(position: Position): List<Position> {
        val cell = board.get(position)
        val boardWidth = board.getWidth()
        val boardHeight = board.getHeight()
        return cell.position
            .getNeighbors(xLimit = boardWidth, yLimit = boardHeight)
    }

    companion object {
        fun generate(width: PositiveNumber, mines: List<Mine>): CellBoard {
            require(mines.size % width.value == 0) { "셀의 개수는 너비의 배수여야 합니다." }

            val mineBoard = Board.of(mines, width)
            val cellBoard = mineToCell(mineBoard)
            return CellBoard(cellBoard)
        }

        private fun mineToCell(mines: Board<Mine>): Board<Cell> {
            return mines.mapPositioned { x, y, mine -> Cell.of(mine, x, y) }
        }
    }
}
