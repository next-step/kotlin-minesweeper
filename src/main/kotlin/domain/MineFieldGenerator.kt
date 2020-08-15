package domain

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(width: Int, height: Int, minesCount: Int): MineField {
        val positions = Position.createAll(width, height)
        val minesPosition = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val normalBlocks = Block.ofNormalsFrom(positions - minesPosition)
        val mineBlocks = Block.ofMinesFrom(minesPosition)
        return MineField((normalBlocks + mineBlocks).sortedBy { it.position })
    }
}
