package domain.field

import domain.block.Blocks
import domain.block.Position

data class MineField(
    private val rectangle: Rectangle,
    val blocks: Blocks
) {
    fun getWidth() = rectangle.width

    fun getHeight() = rectangle.height

    fun getBlocks() = blocks.values

    fun open(position: Position): MineField = copy(blocks = blocks.open(position))

    fun isAllNormalBlocksOpened(): Boolean = blocks.isAllNormalBlocksOpened()
}
