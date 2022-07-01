package minesweeper.domain.cell

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    fun open(position: Position): Cell {
        val cell = findCell(position).open()
        if (cell is Cell.Empty) {
            cell.openNearbyCells()
        }
        return cell
    }

    fun hasClosedEmptyCell() = cells.any { it is Cell.Empty && it.isClosed() }

    private fun Cell.Empty.openNearbyCells() {
        if (this.numberOfNearbyMines != 0) {
            return
        }
        this.getNearbyCells().forEach { nearbyCell ->
            nearbyCell
                .takeIf { nearbyCell is Cell.Empty && nearbyCell.isClosed() }
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
        fun of(width: Int, height: Int, numberOfMines: Int, cellMaker: CellMaker): Cells {
            val cells = cellMaker.make(width, height, numberOfMines)
            return Cells(cells).apply { NearbyMineCounter.count(this) }
        }
    }
}
