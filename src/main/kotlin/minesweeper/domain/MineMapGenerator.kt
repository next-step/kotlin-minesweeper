package minesweeper.domain

object MineMapGenerator {
    fun generate(minePositions: Positions, emptyPositions: Positions): MineMap {
        val mineMap = MineMap()
        minePositions.forEach { mineMap.plantCell(it, Mine()) }
        emptyPositions.forEach { mineMap.plantCell(it, Empty()) }
        return mineMap
    }
}
