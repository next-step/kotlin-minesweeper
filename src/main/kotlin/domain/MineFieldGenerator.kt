package domain

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(width: Int, height: Int, minesCount: Int): MineField {
        val positions = Position.createAll(width, height)
        val minesPosition = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val normalBlocks = (positions - minesPosition).map { Block(it, false) }
        val mineBlocks = minesPosition.map { Block(it, true) }
        return MineField((normalBlocks + mineBlocks).sortedBy { it.position })
    }
}
