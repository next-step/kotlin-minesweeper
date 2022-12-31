package domain

import domain.block.Block
import domain.block.OpenAbleBlock

data class Row(
    val cells: List<Block>
) {
    fun isAllOpened(): Boolean {
        return cells
            .filterIsInstance<OpenAbleBlock>()
            .all { it.isOpened() }
    }
}
