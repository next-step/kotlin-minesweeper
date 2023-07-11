package minesweeper.domain.board

import minesweeper.domain.vo.Position
import minesweeper.domain.vo.PositionX
import minesweeper.domain.vo.PositionY
import minesweeper.domain.vo.PositiveNumber

class CellBoard(val board: List<List<Cell>>) {
    val width = board.getOrNull(0)?.size ?: 0

    val height = board.size

    fun getNeighbors(position: Position): List<Cell> {
        val cell = getCell(position)
        return cell.position
            .getNeighbors(xLimit = width, yLimit = height)
            .map { board[it.y.value][it.x.value] }
    }

    private fun getCell(position: Position) = board[position.y.value][position.x.value]

    companion object {
        fun generate(width: PositiveNumber, mines: List<Mine>): CellBoard {
            require(mines.size % width.value == 0) { "셀의 개수는 너비의 배수여야 합니다." }

            val mineBoard = mines.chunked(width.value)
            val cellBoard = minesToCells(mineBoard)
            return CellBoard(cellBoard)
        }

        private fun minesToCells(mines: List<List<Mine>>): List<List<Cell>> {
            return mines.mapIndexed { y, minesLine ->
                minesLine.mapIndexed { x, mine ->
                    Cell.of(mine, PositionX(x), PositionY(y))
                }
            }
        }
    }
}
