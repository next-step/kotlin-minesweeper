package domain.field

import domain.block.Blocks

data class MineField(
    private val rectangle: Rectangle,
    val blocks: Blocks
) {
    fun getWidth() = rectangle.width

    fun getHeight() = rectangle.height

    fun getBlocks() = blocks.values
}
