package minesweeper.domain

object MineMapGenerator {
    fun generate(minePositions: Positions, emptyPositions: Positions): MineMap {
        val mineMap = MineMap()
        minePositions.forEach { mineMap.plantCell(it, Cell(CellState.MINE)) }
        emptyPositions.forEach { mineMap.plantCell(it, Cell(CellState.EMPTY)) }
        return mineMap
    }
}
