package minesweeper.model.board

import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellBuilder
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class RandomBoard(area: Area, mineCount: Int) : Board(area) {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard())
    private var realCells: Cells? = null
    private val initialCells = Cells(
        List(area.cellCount) { index -> Cell.Empty(area[index]) }
    )
    override val cells: Cells
        get() = realCells ?: initialCells

    override fun openCell(coordinate: Coordinate) {
        if (coordinate !in this.area) {
            return
        }
        this.realCells ?: createRealCells(forceSafeCellCoordinate = coordinate)
        super.openCell(coordinate)
    }

    private fun createRealCells(forceSafeCellCoordinate: Coordinate) {

        val mineCellCoordinates = area.shuffled()
            .filter { it != forceSafeCellCoordinate }
            .take(mineCount)

        val cellBuilder = CellBuilder(this.area) { coordinate -> coordinate in mineCellCoordinates }
        this.realCells = Cells(this.area.map(cellBuilder::createCell))
    }

    companion object {
        private const val COUNT_OF_FORCE_SAFE_CELL = 1
        fun Area.maxMineCountInRandomBoard(): Int = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}
