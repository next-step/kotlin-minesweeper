package domain

import domain.block.Block
import domain.block.OpenAbleBlock
import domain.pos.Pos

data class Row(
    val cells: List<Block>
) {
    constructor(vararg blocks: Block) : this(blocks.toList())

    fun blockOf(pos: Pos): Block {
        return cells[pos.value]
    }

    fun isRangeLessThen(value: Int): Boolean {
        return value < cells.size
    }

    fun isAllOpened(): Boolean {
        return cells
            .filterIsInstance<OpenAbleBlock>()
            .all { it.isOpened() }
    }
}
