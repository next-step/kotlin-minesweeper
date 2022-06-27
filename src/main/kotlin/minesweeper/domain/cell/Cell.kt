package minesweeper.domain.cell

import minesweeper.domain.board.MineMaker

sealed class Cell(val position: Position) {
    private var state: CellStatus = CellStatus.CLOSE

    fun open(): Cell {
        check(state != CellStatus.OPEN) { "cell (${position.x}, ${position.y}) was already opened." }
        state = CellStatus.OPEN
        return this
    }

    fun isClosed() = this.state == CellStatus.CLOSE

    fun isOpen() = this.state == CellStatus.OPEN
}

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    fun open(position: Position): Cell {
        val cell = findCell(position).open()
        if (cell is Empty) {
            cell.openNearbyCells()
        }
        return cell
    }

    fun hasClosedEmptyCell() = cells.any { it is Empty && it.isClosed() }

    private fun Empty.openNearbyCells() {
        if (this.numberOfNearbyMines != 0) {
            return
        }
        getNearbyCells(this).forEach { nearbyCell ->
            nearbyCell
                .takeIf { nearbyCell is Empty && nearbyCell.isClosed() }
                ?.let { open(nearbyCell.position) }
        }
    }

    fun getNearbyCells(cell: Cell): Cells {
        val width = last().position.x + 1
        val height = last().position.y + 1
        val nearbyPositions = getNearbyPositions(cell, width, height)

        return Cells(nearbyPositions.map { findCell(it) })
    }

    private fun getNearbyPositions(
        cell: Cell,
        width: Int,
        height: Int,
    ) = NearbyDirection.values().mapNotNull {
        val nearbyX = cell.position.x + it.x
        val nearbyY = cell.position.y + it.y
        val nearbyIndex = nearbyY * width + nearbyX

        if (nearbyX.isBetweenRange(width) && nearbyY.isBetweenRange(height) && nearbyIndex < size) {
            Position(nearbyX, nearbyY)
        } else null
    }

    private fun findCell(position: Position) =
        cells.find { it.position == position } ?: throw IllegalArgumentException("no cells found in that position.")

    private fun Int.isBetweenRange(limit: Int) = this in 0 until limit

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int, mineMaker: MineMaker): Cells {
            val cells = initCells(width, height)
            val mineCells = mineMaker.createMines(width, height, numberOfMines)

            mineCells.forEach { mine ->
                val index = mine.position.y * width + mine.position.x
                cells[index] = mine
            }
            return Cells(cells)
        }

        private fun initCells(width: Int, height: Int): MutableList<Cell> {
            val size = width * height
            return MutableList(size) {
                val x = it % width
                val y = it / width
                val position = Position(x, y)
                Empty(position)
            }
        }
    }
}
