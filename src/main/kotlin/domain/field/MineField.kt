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

    fun open(position: Position): MineField {
        return copy(blocks = blocks.open(position))
    }
}
