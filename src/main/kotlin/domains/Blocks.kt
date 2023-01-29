package domains

@JvmInline
value class Blocks(val values: List<Block>) {

    fun getBlockByPosition(position: Position): Block {
        return this.values.single { it.position == position }
    }

    fun findNormalBlocks(): List<NormalBlock> {
        return values.filterIsInstance<NormalBlock>()
    }

    fun findMineBlocks(): List<MineBlock> {
        return values.filterIsInstance<MineBlock>()
    }
}
