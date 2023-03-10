package domains

@JvmInline
value class Blocks(val values: List<Block>) {

    fun isMineOpen(): Boolean {
        val mineBlocks = values.filterIsInstance<MineBlock>()
        mineBlocks.firstOrNull { it.isOpened } ?: return false
        return true
    }

    fun isAllOpenNormalBlock(): Boolean {
        val normalBlocks = values.filterIsInstance<NormalBlock>()
        if (!normalBlocks.all { it.isOpened }) return false
        return true
    }

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
