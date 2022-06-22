package minesweeper.model.board

import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.Position

class RandomMineBoard(area: Area, private val mineCount: Int) : Board(area) {

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
}
