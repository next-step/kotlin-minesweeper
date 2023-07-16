package minesweeper.domain

import java.util.LinkedList
import java.util.Queue

class MineBoard(
    val boardInfo: List<BoardRow>,
) {
    val height: Int
        get() = boardInfo.size

    val width: Int
        get() = boardInfo[0].rowInfo.size

    fun openCell(point: Point): Result {
        val queue: Queue<Cell> = LinkedList()
        queue.add(getCell(point))

        while (queue.isNotEmpty()) {
            val cell = queue.poll()
            if (cell.isOpen) continue

            val result = cell.open()

            if (result == Result.LOSE) {
                return Result.LOSE
            }

            if (cell is EmptyCell && cell.mineCount == 0) {
                addCellsToQueue(queue, cell)
            }
        }

        return if (isWin()) Result.WIN else Result.CONTINUE
    }

    private fun addCellsToQueue(queue: Queue<Cell>, cell: Cell) {
        cell.point.getAdjacentPoints().forEach { adjacentPoint ->
            if (adjacentPoint.isWithin(height, width)) {
                queue.add(getCell(adjacentPoint))
            }
        }
    }

    private fun getCell(point: Point): Cell {
        return boardInfo[point.row].rowInfo[point.col]
    }

    private fun isWin(): Boolean {
        return boardInfo.flatMap { it.rowInfo }.all { it.isOpen || it is MineCell }
    }
}
