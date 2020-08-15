package domain

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(rectangle: Rectangle, minesCount: Int): MineField {
        val positions = Position.createAll(rectangle)
        val minesPosition = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val normalBlocks = Block.ofNormalsFrom(positions - minesPosition)
        val mineBlocks = Block.ofMinesFrom(minesPosition)
        return MineField(rectangle, (normalBlocks + mineBlocks).sortedBy { it.position })
    }
}
