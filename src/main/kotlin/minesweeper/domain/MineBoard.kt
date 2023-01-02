package minesweeper.domain

class MineBoard(
    private val mineMap: MineMap,
) {
    fun openCell(position: Position): CellOpenResult {
        val cell = mineMap.openCell(position)
        val cellOpenResult = cell.openResult()
        if (cellOpenResult.noNearMine()) {
            spreadOpenCell(position)
        }
        return cellOpenResult
    }

    private fun spreadOpenCell(position: Position) {
        mineMap.getClosedNearPositions(position)
            .forEach { openCell(it) }
    }

    fun snapshot() = mineMap.snapshot()

    fun isNotGameClear(): Boolean = mineMap.isNotGameClear()

}
