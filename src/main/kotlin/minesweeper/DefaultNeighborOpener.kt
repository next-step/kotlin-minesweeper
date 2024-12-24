package minesweeper

class DefaultNeighborOpener(private val cells: Cells) : CellOpener {
    override fun openNeighborCells(neighbors: List<Position>) {
        neighbors
            .mapNotNull { cells.values[it.key()] }
            .forEach { openCellRecursive(it) }
    }

    override fun open(position: Position): OpenState {
        val cell = cells.at(position)
        if (cell.isOpen) return OpenState.CONTINUE

        return when (cell) {
            is MineCell -> {
                OpenState.LOSE
            }

            is NumberCell -> {
                cell.open()
                openNeighborCells(cell.neighbors())
                if (cells.allNonMineCellsOpened()) OpenState.ALL_DONE else OpenState.CONTINUE
            }
        }
    }

    private fun openCellRecursive(cell: Cell) {
        if (cell.isOpen || cells.isMineCell(cell)) return

        if (cell.isOpenable) {
            cell.open()
            openNeighborCells(cell.neighbors())
        }
    }
}
