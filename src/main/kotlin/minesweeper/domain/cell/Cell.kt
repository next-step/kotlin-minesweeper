package minesweeper.domain.cell

import minesweeper.domain.board.MineMaker
import minesweeper.domain.board.NearbyMineCounter

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
        this.getNearbyCells().forEach { nearbyCell ->
            nearbyCell
                .takeIf { nearbyCell is Empty && nearbyCell.isClosed() }
                ?.let { open(nearbyCell.position) }
        }
    }

    fun Cell.getNearbyCells(): Cells {
        val nearbyPositions = cells
            .map { it.position }
            .intersect(this.position.getNearbyPosition())
        return Cells(nearbyPositions.map { findCell(it) })
    }

    private fun findCell(position: Position): Cell =
        cells.find { it.position == position } ?: throw IllegalArgumentException("no cells found in that position.")

    companion object {
        fun of(width: Int, height: Int, numberOfMines: Int, mineMaker: MineMaker): Cells {
            val cells = initCells(width, height)
            val mineCells = mineMaker.createMines(width, height, numberOfMines)

            mineCells.forEach { mine ->
                val index = mine.position.y * width + mine.position.x
                cells[index] = mine
            }
            return Cells(cells).apply { NearbyMineCounter.count(this) }
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
