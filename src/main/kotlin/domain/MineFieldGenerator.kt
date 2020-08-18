package domain

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(rectangle: Rectangle, minesCount: Int): MineField {
        val positions = rectangle.getAllPositions()
        val minePositions = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val normalBlocks: List<Block> = (positions - minePositions).map { NormalBlock(it, 0) }
        val mineBlocks = Mine.from(minePositions)
        return MineField(rectangle, (normalBlocks + mineBlocks).sortedBy { it.position })
    }
}
