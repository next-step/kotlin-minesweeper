package domain

import kotlin.math.max
import kotlin.math.min

class MineSweeperBoard(boardSize: BoardSize) {
    private val _board = Array(boardSize.height) { Array(boardSize.width) { Cell.createNormalCell() } }

    val width: Int
        get() = _board[0].size

    val height: Int
        get() = _board.size

    fun putMines(vararg position: Position) {
        position.forEach {
            putMine(it)
        }
    }

    fun putMine(position: Position) {
        _board[position.y][position.x] = Cell.createMineCell()
    }

    fun isMine(position: Position): Boolean {
        return _board[position.y][position.x] is Cell.MineCell
    }

    fun getCell(position: Position): Cell {
        return _board[position.y][position.x]
    }

    fun getRow(y: Int): List<Cell> {
        return _board[y].toList()
    }

    fun getMineCountAround(position: Position): Int {
        return getAroundPositions(position).map {
            if (isMine(it)) 1 else 0
        }.sum()
    }

    private fun getAroundPositions(position: Position): List<Position> {
        return (max(position.y - 1, 0)..min(position.y + 1, height - 1)).flatMap { y ->
            (max(position.x - 1, 0)..min(position.x + 1, width - 1)).map { x ->
                Position(x, y)
            }.filter {
                it != position
            }
        }
    }
}
