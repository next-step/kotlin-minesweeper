package minesweeper.domain

class MineMapGenerator(
    private val mineMapMeta: MineMapMeta
) {
    fun generate(minePositions: Set<Position>, emptyPositions: Set<Position>): MineMap {
        require(minePositions.intersect(emptyPositions).isEmpty()) { "지뢰와 빈 공간은 겹칠 수 없습니다." }
        require(minePositions.size + emptyPositions.size == mineMapMeta.getCellCount()) { "지뢰와 빈 공간은 주어진 지뢰맵 크기와 같아야 합니다" }

        val mineMap = MineMap()
        minePositions.forEach { mineMap.plantCell(it, Cell(CellState.MINE)) }
        emptyPositions.forEach { mineMap.plantCell(it, Cell(CellState.EMPTY)) }
        return mineMap
    }
}
