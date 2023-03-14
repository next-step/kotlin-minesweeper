package domains

@JvmInline
value class Blocks(val values: List<Block>) {

    fun isMineOpen(): Boolean =
        values
            .filterIsInstance<MineBlock>()
            .any { it.isOpened }

    fun isAllOpenNormalBlock(): Boolean =
        values
            .filterIsInstance<NormalBlock>()
            .all { it.isOpened }

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
