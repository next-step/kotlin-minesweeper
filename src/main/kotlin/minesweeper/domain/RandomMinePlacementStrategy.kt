package minesweeper.domain

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun selectIndices(allCells: MutableList<Cell>, mineCount: Int): List<Int> {
        return List(mineCount) {
            (allCells.indices).random()
        }
    }
}
