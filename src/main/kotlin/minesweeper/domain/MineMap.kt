package minesweeper.domain

class MineMap(
    private val mineCells: HashMap<Position, Cell>,
) {
    private val mineCount: Int
        get() = mineCells.values
            .count { it is MineCell }

    fun openCell(position: Position): Cell = getCell(position).run {
        mineCells[position] = this.copyWithOpen()
        this.copyWithOpen()
    }

    private fun getCell(position: Position): Cell =
        mineCells[position]
            ?: throw IllegalStateException("해당 위치의 셀을 찾을 수 없습니다. 위치: (${position.row},${position.column})")


    fun getClosedNearPositions(position: Position) = position.getNearPositions()
        .filter { mineCells.keys.contains(it) }
        .filter { getCell(it).state == CellState.CLOSED }

    fun snapshot() = mineCells.keys.sortedBy { it.column }
        .sortedBy { it.row }
        .map { getCell(it) }

    fun countClosedCellNotEqualsMineCell() = mineCells.values.count { it.state == CellState.CLOSED } != mineCount

    companion object {
        fun createMineMap(height: Int, width: Int, mineCount: Int): MineMap {
            val positions = Positions(height, width)
            val mineCells: HashMap<Position, Cell> = linkedMapOf(
                *createPositionToCells(positions.all(), positions.getRandoms(mineCount))
            )
            return MineMap(mineCells)
        }

        private fun createPositionToCells(
            allPositions: List<Position>,
            minePositions: List<Position>,
        ): Array<Pair<Position, Cell>> = allPositions.map { PositionToCell(it, minePositions).pair }.toTypedArray()
    }
}
