package domain

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(rectangle: Rectangle, minesCount: Int): MineField {
        val positions = rectangle.getAllPositions()
        val minePositions = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val normalBlocks = createNormalBlocks(positions, minePositions)
        val mineBlocks = Mine.from(minePositions)
        return MineField(rectangle, (normalBlocks + mineBlocks).sortedBy { it.position })
    }

    private fun createNormalBlocks(allPositions: List<Position>, minePositions: List<Position>): List<Block> {
        val normalPositions = allPositions - minePositions
        return normalPositions.map { it.surroundings() }
            .map { countPositionsContainsMines(it, minePositions) }
            .zip(normalPositions) { minesCount, position -> NormalBlock(position, minesCount) }
    }

    private fun countPositionsContainsMines(positions: List<Position>, minePositions: List<Position>): Int {
        return positions.count { minePositions.contains(it) }
    }
}
