package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class RandomBoard(area: Area, mineCount: Int) : Board(area) {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard())
    private var realCells: Cells? = null
    private val initialCells = Cells(
        List(area.cellCount) { index -> Cell.Safe(area[index], SurroundMineCount(0)) }
    )
    override val cells: Cells
        get() = realCells ?: initialCells

    override fun openCell(position: Position) {
        if (position !in this.area) {
            return
        }
        this.realCells ?: createRealCells(forceSafePosition = position)
        super.openCell(position)
    }

    private fun createRealCells(forceSafePosition: Position) {

        val minePositions = area.shuffled()
            .filter { it != forceSafePosition }
            .take(mineCount)

        val cellBuilder = CellBuilder(this.area) { position -> position in minePositions }
        this.realCells = Cells(this.area.map(cellBuilder::createCell))
    }

    companion object {
        private const val COUNT_OF_FORCE_SAFE_CELL = 1
        fun Area.maxMineCountInRandomBoard(): Int = this.cellCount - COUNT_OF_FORCE_SAFE_CELL
    }
}
